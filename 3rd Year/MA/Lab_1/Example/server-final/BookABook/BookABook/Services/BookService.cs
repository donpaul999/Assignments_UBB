using BookABook.Models;
using System;
using System.Collections.Generic;
using System.Linq;

namespace BookABook.Services
{
    public class BookService : IBookService
    {
        private BookContext context;

        public BookService(BookContext context)
        {
            this.context = context;
        }

        public Book Create(Book book)
        {
            try
            {
                context.Books.Add(book);
                context.SaveChanges();
            }
            catch (Exception)
            {
                return null;
            }

            return book;
        }

        public List<Book> GetAll()
        {
            return context.Books.ToList();
        }

        public Book Update(Book book)
        {
            if (!context.Books.Any(book => book.Id == book.Id))
                return null;

            context.Books.Update(book);
            context.SaveChanges();

            return book;
        }
    }
}