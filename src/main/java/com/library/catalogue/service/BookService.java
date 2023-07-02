package com.library.catalogue.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.catalogue.dao.BookDao;
import com.library.catalogue.dto.inbound.BookEditDto;
import com.library.catalogue.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ObjectMapper dtoToModelMapper;

    public ResponseEntity<Book> getBook(Long isbn) {
        Optional<Book> book = bookDao.findById(isbn);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseThrow(() -> new ResourceNotFoundException("Book not found for this isbn: " + isbn));
    }

    public ResponseEntity<Long> addBook(Book book) {
        Long isbn = book.getIsbn();

        if (bookDao.existsById(isbn)) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            bookDao.save(book);
            return new ResponseEntity<>(isbn, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Void> deleteBook(Long isbn) {
        if (bookDao.existsById(isbn)) {
            bookDao.deleteById(isbn);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Long> editBook(BookEditDto bookEditDto) throws JsonMappingException {
        Long isbn = bookEditDto.getIsbn();
        Book book = bookDao.findById(isbn).orElseThrow(() -> new ResourceNotFoundException("Book not found for this isbn: " + isbn));
        mapBookEditDtoToBook(bookEditDto, book);
        bookDao.save(book);
        return new ResponseEntity<>(isbn, HttpStatus.OK);
    }

    private void mapBookEditDtoToBook(BookEditDto bookEditDto, Book book) throws JsonMappingException {
        dtoToModelMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        dtoToModelMapper.updateValue(book, bookEditDto);
    }
}