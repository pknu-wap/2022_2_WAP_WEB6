package com.web.backend.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookApiController {

    @Autowired
    private BookService bookService;

    @PostMapping("/api/book/books")
    public ResponseEntity<BookDto> register(@RequestBody BookDto bookDto){

        BookDto createdDto = bookService.register(bookDto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);

    }




}
