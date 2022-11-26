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

    @GetMapping("/api/book/title/{title}/authors/{authors}")
    public ResponseEntity check(@PathVariable String title, @PathVariable String authors) {
        System.out.println(title);
        System.out.println(authors);

        bookService.checkBook(title, authors);

        return ResponseEntity.status(HttpStatus.OK).body("hi");

    }

}
