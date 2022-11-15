package com.web.backend.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter

public class BookDto {
    private Long id;
    private String title;

    public static BookDto createBookDto(BookEntity created) {
        return new BookDto(
                created.getId(),
                created.getTitle()
        );
    }
}
