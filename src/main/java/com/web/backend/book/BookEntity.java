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
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;
    private String title;


    public static BookEntity createBook(BookDto bookDto) {
        return new BookEntity(
                bookDto.getId(),
                bookDto.getTitle()
        );
    }

}
