package com.library.catalogue.web.controller;

import com.library.catalogue.dto.inbound.AuthorDto;
import com.library.catalogue.dto.inbound.BookEditDto;
import com.library.catalogue.dto.inbound.PublicationYearDto;
import com.library.catalogue.model.Book;
import com.library.catalogue.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable Long isbn) {
        return bookService.getBook(isbn);
    }

    @GetMapping("/author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestBody AuthorDto authorDto) {
        return bookService.getBooksByAuthor(authorDto);
    }

    @GetMapping("/publication-year/between")
    public ResponseEntity<List<Book>> getBooksInPublicationRange(@RequestBody PublicationYearDto publicationYearDto) {
        return bookService.getBooksInPublicationRange(publicationYearDto);
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PatchMapping("/edit")
    public ResponseEntity<Long> editBook(@RequestBody BookEditDto bookEditDto) {
        return bookService.editBook(bookEditDto);
    }

    @DeleteMapping("/delete/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long isbn) {
        return bookService.deleteBook(isbn);
    }
}