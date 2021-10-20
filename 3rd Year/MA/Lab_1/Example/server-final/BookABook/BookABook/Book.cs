using System;
using System.Collections.Generic;

#nullable disable

namespace BookABook
{
    public partial class Book
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Author { get; set; }
        public string PublishDate { get; set; }
        public string IsBooked { get; set; }
        public Guid UserId { get; set; }
    }
}
