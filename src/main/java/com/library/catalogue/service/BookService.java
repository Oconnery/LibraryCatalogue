package com.library.catalogue.service;

import com.library.catalogue.dao.BookDao;
import com.library.catalogue.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public ResponseEntity<Book> getBook(Long ibsn) {
        Optional<Book> book = bookDao.findById(ibsn);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new Book(), HttpStatus.NO_CONTENT));
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
}