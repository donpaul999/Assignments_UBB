using System;
using System.ComponentModel.DataAnnotations;

namespace BookABook.Models
{
    public class Book
    {
        [Key]
        public int Id { get; set; }

        [Required]
        public string Name { get; set; }

        [Required]
        public string Author { get; set; }

        [Required]
        public string publishDate { get; set; }

        [Required]
        public string isBooked { get; set; }
        
        public Guid UserId { get; set; }
    }
}