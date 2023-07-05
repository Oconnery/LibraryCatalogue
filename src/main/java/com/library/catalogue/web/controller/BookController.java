package com.library.catalogue.web.controller;

import com.library.catalogue.dto.inbound.AuthorDto;
import com.library.catalogue.dto.inbound.BookCreationDto;
import com.library.catalogue.dto.inbound.BookEditDto;
import com.library.catalogue.dto.inbound.PublicationYearDto;
import com.library.catalogue.model.Book;
import com.library.catalogue.service.BookService;
import com.library.catalogue.validation.constraint.ValidateIsbnFormat;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/library/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBook(@ValidateIsbnFormat @PathVariable Long isbn) {
        return bookService.getBook(isbn);
    }

    @GetMapping("/author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@Valid @RequestBody AuthorDto authorDto) {
        return bookService.getBooksByAuthor(authorDto);
    }

    @GetMapping("/publication-year/between")
    public ResponseEntity<List<Book>> getBooksInPublicationRange(@Valid @RequestBody PublicationYearDto publicationYearDto) {
        return bookService.getBooksInPublicationRange(publicationYearDto);
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addBook(@Valid @RequestBody BookCreationDto bookCreationDto) {
        return bookService.addBook(bookCreationDto);
    }

    @PatchMapping("/edit")
    public ResponseEntity<Long> editBook(@Valid @RequestBody BookEditDto bookEditDto) {
        return bookService.editBook(bookEditDto);
    }

    @DeleteMapping("/delete/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable @ValidateIsbnFormat Long isbn) {
        return bookService.deleteBook(isbn);
    }
}