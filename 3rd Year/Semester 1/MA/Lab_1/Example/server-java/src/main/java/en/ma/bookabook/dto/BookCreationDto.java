package en.ma.bookabook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCreationDto {
    private String id;
    private String name;
    private String author;
    private String publishDate;
    private String isBooked;
}