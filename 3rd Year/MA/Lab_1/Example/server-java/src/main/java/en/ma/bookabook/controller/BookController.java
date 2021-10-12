package en.ma.bookabook.controller;

import en.ma.bookabook.dto.BookCreationDto;
import en.ma.bookabook.dto.BookDto;
import en.ma.bookabook.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController  implements BookControllerAPI{

    private final BookService appService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBooks() {
        return appService.getBooks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto addBook(@RequestBody BookCreationDto bookCreationDto) {
        return appService.addBook(bookCreationDto);
    }

    @DeleteMapping("/{appId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeBook(@PathVariable int appId) {
        appService.removeBook(appId);
    }

    @PutMapping("/{appId}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto updateBook(@RequestBody BookCreationDto appCreationDto, @PathVariable int appId) {
        return appService.updateBook(appCreationDto, appId);
    }
}