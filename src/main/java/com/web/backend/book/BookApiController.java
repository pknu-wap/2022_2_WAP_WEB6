package com.web.backend.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookApiController {

    @Autowired
    private BookService bookService;

    @PostMapping("/api/book/books")
    public ResponseEntity<BookDto> register(@RequestBody BookDto bookDto) {

        BookDto createdDto = bookService.register(bookDto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);

    }

    @PostMapping("/api/check/book")
//    public ResponseEntity check(@PathVariable String title, @PathVariable String authors) {
    public ResponseEntity check(@RequestBody BookDto bookDto) {

//        System.out.println(title);
//        System.out.println(authors);
//
        // 책 있으면 True 없으면 False
        boolean result = bookService.checkBook(bookDto.getTitle(), bookDto.getAuthors());

        if (result == true) {

        } else {

        }
        return ResponseEntity.status(HttpStatus.OK).body("hi");

    }

}
