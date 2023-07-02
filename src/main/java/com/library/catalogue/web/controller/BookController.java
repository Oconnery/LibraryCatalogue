package com.library.catalogue.web.controller;

import com.library.catalogue.model.Book;
import com.library.catalogue.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/library/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable Long isbn) {
        return bookService.getBook(isbn);
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
}