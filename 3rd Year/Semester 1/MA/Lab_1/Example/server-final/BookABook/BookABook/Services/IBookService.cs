using BookABook.Models;
using System.Collections.Generic;

namespace BookABook.Services
{
    public interface IBookService
    {
        List<Book> GetAll();
        
        Book GetBook(int id);

        Book Create(Book book);

        Book Update(Book book);
        
        Book Remove(int Id);
        
        List<Book> GetSomeBooks(int start, int count);
        
        List<Book> GetAvailable();

        List<Book> GetRelated();
    }
}