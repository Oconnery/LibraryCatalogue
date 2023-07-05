package com.library.catalogue.service;

import com.library.catalogue.dao.BookDao;
import com.library.catalogue.dto.inbound.AuthorDto;
import com.library.catalogue.dto.inbound.BookCreationDto;
import com.library.catalogue.dto.inbound.BookEditDto;
import com.library.catalogue.dto.inbound.PublicationYearDto;
import com.library.catalogue.mappers.BookMapper;
import com.library.catalogue.model.Book;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    public BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    @Autowired
    private BookDao bookDao;

    public ResponseEntity<Book> getBook(Long isbn) {
        Optional<Book> book = bookDao.findById(isbn);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseThrow(() -> new ResourceNotFoundException("isbn: " + isbn));
    }

    public ResponseEntity<Long> addBook(BookCreationDto bookCreationDto) {
        Book book = bookMapper.updateBookFromCreationDto(bookCreationDto);
        bookDao.save(book);
        return new ResponseEntity<>(book.getIsbn(), HttpStatus.CREATED);
    }

    public ResponseEntity<Void> deleteBook(Long isbn) {
        if (bookDao.existsById(isbn)) {
            bookDao.deleteById(isbn);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Long> editBook(BookEditDto bookEditDto) {
        Long isbn = bookEditDto.getIsbn();
        Book book = bookDao.findById(isbn).orElseThrow(() -> new ResourceNotFoundException("isbn: " + isbn));
        bookMapper.updateBookFromEditDto(book, bookEditDto);
        bookDao.save(book);
        return new ResponseEntity<>(book.getIsbn(), HttpStatus.OK);
    }

    public ResponseEntity<List<Book>> getBooksByAuthor(AuthorDto authorDto) {
        return new ResponseEntity<>(bookDao.findByAuthorFirstNameAndAuthorLastName(authorDto.getAuthorFirstName(), authorDto.getAuthorLastName()), HttpStatus.OK);
    }

    public ResponseEntity<List<Book>> getBooksInPublicationRange(PublicationYearDto publicationYearDto) {
        return new ResponseEntity<>(bookDao.findByPublicationYearBetween(
                publicationYearDto.getFromPublicationYear(),
                publicationYearDto.getToPublicationYear()
        ), HttpStatus.OK);
    }

    public boolean isbnExists(Long isbn) {
        return bookDao.existsById(isbn);
    }
}