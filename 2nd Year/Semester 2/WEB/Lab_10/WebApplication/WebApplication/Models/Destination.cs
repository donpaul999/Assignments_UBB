using System;
using System.ComponentModel.DataAnnotations;

namespace WebApplication.Models
{
    public class Destination
    {
        public int id { get; set; }
        
        [Required]
        public String name { get; set; }
        
        [Required]
        public String country { get; set; }
        
        [Required]
        public String description { get; set; }
        
        [Required]
        public String targets { get; set; }
        
        [Required]
        public float costs { get; set; }
    }
}