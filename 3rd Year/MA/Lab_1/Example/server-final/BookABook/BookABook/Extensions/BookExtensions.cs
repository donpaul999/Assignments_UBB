using BookABook.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Security.Claims;

namespace BookABook.Extensions
{
    public static class BookExtensions
    {
        public static Book AttachUserId(
            this Book book,
            ControllerBase controller)
        {
            book.UserId = Guid.Parse(controller.User.FindFirst(ClaimTypes.NameIdentifier).Value);
            return book;
        }
    }
}