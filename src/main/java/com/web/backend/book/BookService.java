package com.web.backend.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;


    @Transactional
    public BookDto register(BookDto bookDto) {
        BookEntity bookEntity = BookEntity.createBook(bookDto);

        BookEntity created = bookRepository.save(bookEntity);

        return BookDto.createBookDto(created);
    }



}
