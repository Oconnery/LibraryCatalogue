package com.library.catalogue.web.controller;

import com.library.catalogue.model.Book;
import com.library.catalogue.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/library/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable Long isbn) {
        return bookService.getBook(isbn);
    }
}
