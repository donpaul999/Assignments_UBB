using BookABook.BookUpdates;
using BookABook.Models;
using BookABook.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;
using BookABook.Extensions;

namespace BookABook.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class BookController : ControllerBase
    {
        private static int BAD_REQUEST_STATUS_CODE = 400;

        private readonly IBookService bookService;
        private readonly IBroadcastHandler<Notification> broadcastHandler;

        public BookController(IBookService bookService, IBroadcastHandler<Notification> broadcastHandler)
        {
            this.bookService = bookService;
            this.broadcastHandler = broadcastHandler;
        }

        [HttpPost]
        public IActionResult CreateBook(Book book)
        {
            var newBook = bookService.Create(book.AttachUserId(this));
            if (newBook == null) return BadRequest();

            broadcastHandler.Broadcast(new Notification{ Event="create", Book=newBook } );

            return Ok(newBook);
        }

        [HttpPut("{id}")]
        public IActionResult UpdateBook(Book book)
        {
            var updatedBook = bookService.Update(book.AttachUserId(this));
            
            if (updatedBook == null) return NotFound();
            
            broadcastHandler.Broadcast(new Notification{ Event="update", Book=updatedBook } );
            
            return Ok(updatedBook);
        }
        
        [HttpDelete("{id}")]
        public IActionResult RemoveBook(int Id)
        {
            var removedBook = bookService.Remove(Id);

            if (removedBook == null) return NotFound();
            
            broadcastHandler.Broadcast(new Notification{ Event="remove", Book=removedBook } );
            
            return Ok(removedBook);
        }
        
        [HttpGet("available")]
        public IActionResult GetAvailableBooks()
        {
            return Ok(bookService.GetAvailable());
        }

        [HttpGet("related")]
        public IActionResult GetRelatedBooks()
        {
            return Ok(bookService.GetRelated());
        }
        
        [HttpGet]
        public IActionResult GetAllBooks()
        {
            return Ok(bookService.GetAll());
        }
        
        [HttpGet("count/{start}/{count}")]
        public IActionResult GetSomeBooks(int start, int count)
        {
            return Ok(bookService.GetSomeBooks(start, count));
        }


        [HttpGet("{id}")]
        public IActionResult GetBook(int id)
        {
            return Ok(bookService.GetBook(id));
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