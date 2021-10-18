using BookABook.Authentication;
using BookABook.BookUpdates;
using BookABook.Models;
using BookABook.Services;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Identity;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.IdentityModel.Tokens;
using Microsoft.OpenApi.Models;
using System.Text;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;
using System.Threading.Tasks;

namespace BookABook
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }
        
        private JwtBearerEvents webSocketBearerHandler = new JwtBearerEvents
        {
            OnMessageReceived = context =>
            {
                if (context.Request.Headers.ContainsKey("sec-websocket-protocol") && context.HttpContext.WebSockets.IsWebSocketRequest)
                {
                    var token = context.Request.Headers["sec-websocket-protocol"].ToString();
                    context.Token = token.Substring(token.IndexOf(',') + 1).Trim();
                    context.Request.Headers["sec-websocket-protocol"] = "access_token";
                }
                return Task.CompletedTask;
            }
        };

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            ConfigureDatabase(services);
            ConfigureAuthentication(services);
            services.AddSignalR();

            services.AddCors(config =>
                config.AddDefaultPolicy(options =>
                    options.AllowAnyOrigin()
                        .AllowAnyMethod()
                        .AllowAnyHeader()));
            services.AddControllers();
            services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new OpenApiInfo { Title = "BookABook", Version = "v1" });
            });

            services.AddSingleton<IBroadcastHandler<Notification>, BroadcastHandler<Notification>>();
            services.AddScoped<IBookService, BookService>();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {

            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
                app.UseSwagger();
                app.UseSwaggerUI(c =>
                    c.SwaggerEndpoint("/swagger/v1/swagger.json", "BookABook v1"));
            }

            app.UseHttpsRedirection();
            app.UseRouting();
            app.UseWebSockets();

            app.UseCors();

            app.UseAuthentication();
            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }

        private void ConfigureDatabase(IServiceCollection services)
        {
            var connection = Configuration.GetConnectionString("BookContext");

            services.AddDbContext<BookContext>(options => options.UseSqlServer(connection));
        }

        private void ConfigureAuthentication(IServiceCollection services)
        {
            services.AddIdentity<ApplicationUser, IdentityRole>()
                .AddEntityFrameworkStores<BookContext>()
                .AddDefaultTokenProviders();

            services.AddAuthentication(options =>
            {
                options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
                options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
                options.DefaultScheme = JwtBearerDefaults.AuthenticationScheme;
            }).AddJwtBearer(options =>
            {
                options.SaveToken = true;
                options.RequireHttpsMetadata = false;
                options.TokenValidationParameters = new TokenValidationParameters
                {
                    ValidateIssuer = true,
                    ValidateAudience = true,
                    ValidAudience = Configuration["JWT:ValidAudience"],
                    ValidIssuer = Configuration["JWT:ValidIssuer"],
                    IssuerSigningKey = new SymmetricSecurityKey(
                        Encoding.UTF8.GetBytes(Configuration["JWT:Secret"]))
                };
                options.Events = webSocketBearerHandler;
            });
        }
    }
}