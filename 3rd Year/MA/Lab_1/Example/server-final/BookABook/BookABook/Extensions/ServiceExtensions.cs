using Microsoft.AspNetCore.Http;
using System;
using System.Security.Claims;

namespace BookABook.Extensions
{
    public static class ServiceExtensions
    {
        public static Guid GetUserId(this IHttpContextAccessor httpContextAccessor)
        {
            return Guid.Parse(httpContextAccessor.HttpContext.User.FindFirst(ClaimTypes.NameIdentifier).Value);
        }
    }
}