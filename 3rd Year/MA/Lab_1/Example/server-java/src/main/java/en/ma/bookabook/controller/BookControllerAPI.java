package en.ma.bookabook.controller;

import en.ma.bookabook.dto.BookCreationDto;
import en.ma.bookabook.dto.BookDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(description = "Book API", tags = {"Book"})

public interface BookControllerAPI {

    @ApiOperation(value = "Get a list of all books")
    List<BookDto> getBooks();

    @ApiOperation(value = "Add a book")
    BookDto addAppointment(BookCreationDto appCreationDto);

    @ApiOperation(value = "Remove an existing book")
    void removeBook(int bookId);

    @ApiOperation(value = "Update an existing book")
    BookDto updateApp(BookCreationDto bookCreationDto, int appId);
}