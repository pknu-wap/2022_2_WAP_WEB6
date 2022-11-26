package com.web.backend.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class BookEntity {
    //(저장 정보 : 제목
    //저자
    //썸네일
    //설명)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;        // 사이트 자체 책 id
    private String title;       // 제목
    private String authors;   // 저자
    private String url;         //썸네일
    private String contents;    //책 설명


    public static BookEntity createBook(BookDto bookDto) {
        return new BookEntity(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthors(),
                bookDto.getUrl(),
                bookDto.getContents()
        );
    }

}
