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
    private String authors;   // 저자
    private String url;         //썸네일
    private String contents;    //책 설명

    public static BookDto createBookDto(BookEntity created) {
        return new BookDto(
                created.getId(),
                created.getTitle(),
                created.getAuthors(),
                created.getUrl(),
                created.getContents()

        );
    }


}
