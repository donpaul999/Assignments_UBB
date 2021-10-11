using BookABook.BookUpdates;
using BookABook.Models;
using BookABook.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace BookABook.Controllers
{
    [Authorize]
    [ApiController]
    [Route("api/[controller]")]
    public class BookController : ControllerBase
    {
        private const int BAD_REQUEST_STATUS_CODE = 400;

        private readonly IBookService bookService;
        private readonly IBroadcastHandler<Book> broadcastHandler;

        public BookController(IBookService bookService, IBroadcastHandler<Book> broadcastHandler)
        {
            this.bookService = bookService;
            this.broadcastHandler = broadcastHandler;
        }

        [HttpPost]
        public IActionResult CreateBook(Book book)
        {
            var newBook = bookService.Create(book);

            if (newBook == null) return BadRequest();

            broadcastHandler.Broadcast(newBook);

            return Ok(newBook);
        }

        [HttpPut]
        public IActionResult UpdateBook(Book book)
        {
            var updatedBook = bookService.Update(book);

            if (updatedBook == null) return NotFound();

            return Ok(updatedBook);
        }

        [HttpGet]
        public IActionResult GetAllBooks()
        {
            return Ok(bookService.GetAll());
        }

        [HttpGet("updates")]
        public async Task GetUpdatesAsync()
        {
            if (!HttpContext.WebSockets.IsWebSocketRequest)
            {
                HttpContext.Response.StatusCode = BAD_REQUEST_STATUS_CODE;
                return;
            }

            using var webSocket = await HttpContext.WebSockets.AcceptWebSocketAsync();

            await broadcastHandler.AddConnection(webSocket);
        }
    }
}