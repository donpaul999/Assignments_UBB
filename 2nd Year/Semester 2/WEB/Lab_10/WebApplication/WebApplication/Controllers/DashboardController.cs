using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace WebApplication.Controllers
{
    public class DashboardController : Controller
    {
        // GET
        [HttpGet]
        public IActionResult Index()
        {
            if (HttpContext.Session.GetString("loggedin") != "yes")
                return Redirect("/Account/Login");
            return View();
        }
    }
}