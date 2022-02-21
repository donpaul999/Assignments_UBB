package en.ma.bookabook.service;

import en.ma.bookabook.dto.BookCreationDto;
import en.ma.bookabook.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getBooks();
    BookDto addBook(BookCreationDto userCreationDto);
    void removeBook(String id);
    BookDto updateBook(BookCreationDto bookCreationDto, int id);
}