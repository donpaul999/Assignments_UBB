using BookABook.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using BookABook.Extensions;
using Microsoft.AspNetCore.Http;

namespace BookABook.Services
{
    public class BookService : IBookService
    {
        private BookContext context;
        private readonly IHttpContextAccessor httpContextAccessor;
        
        public BookService(BookContext context, IHttpContextAccessor httpContextAccessor)
        {
            this.context = context;
            this.httpContextAccessor = httpContextAccessor;
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
            List<Book> books = context.Books.ToList();
            books.Sort((x, y) => x.Id.CompareTo(y.Id));
            return books;
        }
        
        public Book GetBook(int id)
        {
            return context.Books.SingleOrDefault(book => book.Id == id);
        }
        
        public Book Update(Book book)
        {
            if (!context.Books.Any(book => book.Id == book.Id))
                return null;

            context.Books.Update(book);
            context.SaveChanges();

            return book;
        }
        
        public Book Remove(int Id)
        {
            Book book = context.Books.Find(Id);
            if (book is null)
                return null;

            context.Books.Remove(book);
            context.SaveChanges();

            return book;
        }

        public List<Book> GetSomeBooks(int start, int count)
        { 
            List<Book> books = context.Books.ToList();
            books.Sort((x, y) => x.Id.CompareTo(y.Id));
            return books.Skip(start).Take(count).ToList();
        }
        
        public List<Book> GetAvailable()
        {
            var userId = httpContextAccessor.GetUserId();

            return context.Books.Where(book => book.UserId != userId && book.IsBooked == "false").ToList();
        }

        public List<Book> GetRelated()
        {
            var userId = httpContextAccessor.GetUserId();

            return context.Books.Where(book => book.UserId == userId).ToList();
        }
    }
}