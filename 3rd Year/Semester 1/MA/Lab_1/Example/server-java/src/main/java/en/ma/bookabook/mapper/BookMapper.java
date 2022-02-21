package en.ma.bookabook.mapper;

import en.ma.bookabook.dto.BookCreationDto;
import en.ma.bookabook.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book dtoToModel(BookCreationDto bookCreationDto){
        return Book.builder()
                .name(bookCreationDto.getName())
                .getAuthor(bookCreationDto.getAuthor())
                .getPublishDate(bookCreationDto.getPublishDate())
                .getIsBooked(bookCreationDto.getIsBooked())
                .build();
    }

    public BookCreationDto modelToDto(Book book){
        return BookCreationDto.builder()
                .id(book.getId())
                .getName(book.getName())
                .getAuthor(book.getAuthor())
                .getIsBooked(book.getIsBooked())
                .getPublishDate(book.getPublishDate())
                .getIsBooked(book.getIsBooked())
                .build();
    }
}