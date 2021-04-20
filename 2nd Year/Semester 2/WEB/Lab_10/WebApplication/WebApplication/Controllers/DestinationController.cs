using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using Ganss.XSS;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Npgsql;
using WebApplication.Models;

namespace WebApplication.Controllers
{
    public class DestinationController : Controller
    {
        private String con = "Server=ec2-52-50-171-4.eu-west-1.compute.amazonaws.com;Port=5432;Database=dfo8rh2he4q2fl;User ID=iuydvgjxwvhuor;Password=cf76eb6e9569881f134ee4172d8c3e75b92ef27d26b4308da0c036d44e67ee46;SSL Mode=Require;Trust Server Certificate=true";
        HtmlSanitizer sanitizer = new HtmlSanitizer();
        
        // GET
        public IActionResult Index()
        {
            return View();
        }
        
        [HttpGet]
        public IActionResult Add()
        { 
            if (HttpContext.Session.GetString("loggedin") != "yes")
                return Redirect("/Account/Login");
            return View();
        }

        [HttpPost]
        public IActionResult Add(Destination destination)
        {
            if (HttpContext.Session.GetString("loggedin") != "yes")
                return Redirect("/Account/Login");
            
            destination.name = sanitizer.Sanitize(destination.name);
            destination.country = sanitizer.Sanitize(destination.country);
            destination.description = sanitizer.Sanitize(destination.description);
            destination.costs = float.Parse(sanitizer.Sanitize(destination.costs.ToString("N4")));
            destination.targets = sanitizer.Sanitize(destination.targets);
            
            var sql ="INSERT INTO destinations(name, country, targets, description, cost) VALUES ('" + destination.name + "', '" + destination.country + "', '" + destination.targets + "', '" + destination.description + "', " + destination.costs + ")";
            
            using (DbConnection conn = new NpgsqlConnection(con))
            {
                conn.Open();
                using (DbCommand command = conn.CreateCommand())
                {
                    command.CommandText = sql;
                    DbDataReader dr = command.ExecuteReader();
                }
            }
            
            return View();
        }

        
        [HttpGet]
        public IActionResult Delete(int id)
        {
            if (HttpContext.Session.GetString("loggedin") != "yes")
                return Redirect("/Account/Login");

            var sql = "SELECT COUNT(*) FROM destinations";
            using (DbConnection conn = new NpgsqlConnection(con))
            {
                conn.Open();
                using (DbCommand command = conn.CreateCommand())
                {
                    command.CommandText = sql;
                    int number_of_result =  Convert.ToInt32(command.ExecuteScalar());
                    
                    var results_per_page = 4;
                    var page_selected = 1;
                    if (id != 0)
                        page_selected = id;
                    var page_first_result = (page_selected - 1) * results_per_page;
                    int number_of_page = number_of_result / results_per_page;
                    if (number_of_result % results_per_page != 0)
                        number_of_page += 1;
                    var query = "SELECT * FROM destinations LIMIT " + results_per_page + " OFFSET " + page_first_result;
                    List<Destination> results = new List<Destination>();
                    
                    command.CommandText = query;
                    DbDataReader dr = command.ExecuteReader();
                    while (dr.Read())
                    {
                        results.Add(new Destination{id = int.Parse(dr.GetValue(0).ToString()), name = dr.GetValue(1).ToString(), country = dr.GetValue(2).ToString(), description = dr.GetValue(3).ToString(), targets = dr.GetValue(4).ToString(), costs = float.Parse(dr.GetValue(5).ToString())});
                    }

                    var pair = new Tuple<List<Destination>, Tuple<int, int>>(results, new Tuple<int, int>(number_of_page, page_selected));
                    return View(pair);
                }
            }
            
            return View();
        }

        [HttpGet]
        public IActionResult DeleteSpecific(int id)
        {
            if (HttpContext.Session.GetString("loggedin") != "yes")
                return Redirect("/Account/Login");
            
            var sql ="DELETE FROM destinations where id = " + id;
            
            using (DbConnection conn = new NpgsqlConnection(con))
            {
                conn.Open();
                using (DbCommand command = conn.CreateCommand())
                {
                    command.CommandText = sql;
                    DbDataReader dr = command.ExecuteReader();
                }
            }
            
            
            return Redirect("/Destination/Delete");
        }
        
        [HttpGet]
        public IActionResult Update(int id)
        {
            if (HttpContext.Session.GetString("loggedin") != "yes")
                return Redirect("/Account/Login");

            var sql = "SELECT COUNT(*) FROM destinations";
            using (DbConnection conn = new NpgsqlConnection(con))
            {
                conn.Open();
                using (DbCommand command = conn.CreateCommand())
                {
                    command.CommandText = sql;
                    int number_of_result =  Convert.ToInt32(command.ExecuteScalar());
                    
                    var results_per_page = 4;
                    var page_selected = 1;
                    if (id != 0)
                        page_selected = id;
                    var page_first_result = (page_selected - 1) * results_per_page;
                    int number_of_page = number_of_result / results_per_page;
                    if (number_of_result % results_per_page != 0)
                        number_of_page += 1;
                    var query = "SELECT * FROM destinations LIMIT " + results_per_page + " OFFSET " + page_first_result;
                    List<Destination> results = new List<Destination>();
                    
                    command.CommandText = query;
                    DbDataReader dr = command.ExecuteReader();
                    while (dr.Read())
                    {
                        results.Add(new Destination{id = int.Parse(dr.GetValue(0).ToString()), name = dr.GetValue(1).ToString(), country = dr.GetValue(2).ToString(), description = dr.GetValue(3).ToString(), targets = dr.GetValue(4).ToString(), costs = float.Parse(dr.GetValue(5).ToString())});
                    }

                    var pair = new Tuple<List<Destination>, Tuple<int, int>>(results, new Tuple<int, int>(number_of_page, page_selected));
                    return View(pair);
                }
            }

            return View();
        }
        
        [HttpGet]
        
        
        
        [HttpGet]
        public IActionResult Browse()
        {
            if (HttpContext.Session.GetString("loggedin") != "yes")
                return Redirect("/Account/Login");
            return View();
        }
    }
}