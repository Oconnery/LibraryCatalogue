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
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@ValidateIsbnFormat @PathVariable Long isbn) {
        return bookService.getBook(isbn);
    }

    @GetMapping("/author")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBooksByAuthor(@Valid @RequestBody AuthorDto authorDto) {
        return bookService.getBooksByAuthor(authorDto);
    }

    @GetMapping("/publication-year/between")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBooksInPublicationRange(@Valid @RequestBody PublicationYearDto publicationYearDto) {
        return bookService.getBooksInPublicationRange(publicationYearDto);
    }

    @PatchMapping("/borrow/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public Long borrowBook(@ValidateIsbnFormat @PathVariable Long isbn) {
        return bookService.borrowBook(isbn);
    }

    @PatchMapping("/return/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public Long returnBook(@ValidateIsbnFormat @PathVariable Long isbn) {
        return bookService.returnBook(isbn);
    }

    @PatchMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public Long editBook(@Valid @RequestBody BookEditDto bookEditDto) {
        return bookService.editBook(bookEditDto);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addBook(@Valid @RequestBody BookCreationDto bookCreationDto) {
        return bookService.addBook(bookCreationDto);
    }

    @DeleteMapping("/delete/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable @ValidateIsbnFormat Long isbn) {
        bookService.deleteBook(isbn);
    }
}