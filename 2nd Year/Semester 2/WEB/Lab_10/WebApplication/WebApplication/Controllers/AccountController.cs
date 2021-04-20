using System;
using System.Data.Common;
using Microsoft.AspNetCore.Mvc;
using WebApplication.Models;
using System.Data.SqlClient;
using Microsoft.AspNetCore.Session;
using Npgsql;
using Microsoft.AspNetCore.Http;

namespace WebApplication.Controllers
{
    public class AccountController : Controller
    {
        
        private String con = "Server=ec2-52-50-171-4.eu-west-1.compute.amazonaws.com;Port=5432;Database=dfo8rh2he4q2fl;User ID=iuydvgjxwvhuor;Password=cf76eb6e9569881f134ee4172d8c3e75b92ef27d26b4308da0c036d44e67ee46;SSL Mode=Require;Trust Server Certificate=true";
        
        
        // GET
        [HttpGet]
        public IActionResult Login()
        {
            if (HttpContext.Session.GetString("loggedin") == "yes")
                    return Redirect("/Dashboard");
            return View();
        }

        [HttpGet]
        public IActionResult Logout()
        {
            HttpContext.Session.SetString("loggedin", "no");
            return Redirect("/Account/Login");
        }
        
        [HttpPost]
        public ActionResult Verify(Account acc)
        {
            using (DbConnection conn = new NpgsqlConnection(con))
            {
                conn.Open();
                using (DbCommand command = conn.CreateCommand())
                {
                    command.CommandText = "SELECT * FROM users WHERE username = '" + acc.Username + "' and password = '" + acc.Password + "'";
                    DbDataReader dr = command.ExecuteReader();
                    if (dr.Read())
                    {
                        HttpContext.Session.SetString("loggedin", "yes");
                        ViewBag.username = acc.Username;
                        return Redirect("/Dashboard");
                    }
                    else
                    {
                        return View("Error");
                    }
                }
            }
        }
    }
}