using BookABook.Models;
using System.Collections.Generic;

namespace BookABook.Services
{
    public interface IBookService
    {
        List<Book> GetAll();

        Book Create(Book book);

        Book Update(Book book);
    }
}