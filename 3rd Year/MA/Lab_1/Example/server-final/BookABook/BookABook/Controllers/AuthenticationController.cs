using BookABook.Authentication;
using BookABook.Models;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Text;
using System.Threading.Tasks;

namespace BookABook.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AuthenticationController : ControllerBase
    {
        private readonly UserManager<ApplicationUser> userManager;
        private readonly IConfiguration configuration;

        public AuthenticationController(
            UserManager<ApplicationUser> userManager,
            IConfiguration configuration)
        {
            this.userManager = userManager;
            this.configuration = configuration;
        }

        [HttpPost]
        [Route("register")]
        public async Task<IActionResult> Register(User userData)
        {
            var doesUserExist = await userManager.FindByEmailAsync(userData.Email);
            if (doesUserExist != null)
                return Conflict("User already exists!");

            var user = new ApplicationUser
            {
                UserName = userData.Email,
                Email = userData.Email,
                SecurityStamp = Guid.NewGuid().ToString()
            };

            var result = await userManager.CreateAsync(user, userData.Password);
            if (!result.Succeeded)
            {
                var errorList = result.Errors.ToList().Select(error => error.Description);
                var formattedErrors = string.Join("\n", errorList);
                return BadRequest(formattedErrors);
            }

            return Ok();
        }

        [HttpPost]
        [Route("login")]
        public async Task<IActionResult> Login(User userData)
        {
            var user = await userManager.FindByEmailAsync(userData.Email);
            if (user == null)
                return Unauthorized();

            var isPasswordCorrect = await userManager.CheckPasswordAsync(user, userData.Password);
            if (!isPasswordCorrect)
                return Unauthorized();

            var authenticationClaims = new List<Claim>
            {
                new Claim(ClaimTypes.NameIdentifier, user.Id),
                new Claim(ClaimTypes.Email, user.Email),
                new Claim(JwtRegisteredClaimNames.Jti, Guid.NewGuid().ToString())
            };

            var authenticationSigningKey =
                new SymmetricSecurityKey(Encoding.UTF8.GetBytes(configuration["JWT:Secret"]));

            var token = new JwtSecurityToken(
                issuer: configuration["JWT:ValidIssuer"],
                audience: configuration["JWT:ValidAudience"],
                expires: DateTime.Now.AddHours(12),
                claims: authenticationClaims,
                signingCredentials: new SigningCredentials(
                    authenticationSigningKey,
                    SecurityAlgorithms.HmacSha256)
            );

            return Ok(new
            {
                Id = user.Id,
                Token = new JwtSecurityTokenHandler().WriteToken(token),
                Expiration = token.ValidTo
            });
        }
    }
}