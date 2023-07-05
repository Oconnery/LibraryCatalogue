package com.library.catalogue.service;

import com.library.catalogue.dao.BookDao;
import com.library.catalogue.dto.inbound.AuthorDto;
import com.library.catalogue.dto.inbound.BookCreationDto;
import com.library.catalogue.dto.inbound.BookEditDto;
import com.library.catalogue.dto.inbound.PublicationYearDto;
import com.library.catalogue.exception.custom.BookIsAlreadyBorrowedException;
import com.library.catalogue.exception.custom.BookIsNotBorrowedException;
import com.library.catalogue.mappers.BookMapper;
import com.library.catalogue.model.Book;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    public BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    @Autowired
    private BookDao bookDao;

    public Book getBook(Long isbn) {
        return bookDao.findById(isbn).orElseThrow(() -> new ResourceNotFoundException("isbn: " + isbn));
    }

    public List<Book> getBooksByAuthor(AuthorDto authorDto) {
        return bookDao.findByAuthorFirstNameAndAuthorLastName(authorDto.getAuthorFirstName(), authorDto.getAuthorLastName());
    }

    public List<Book> getBooksInPublicationRange(PublicationYearDto publicationYearDto) {
        return bookDao.findByPublicationYearBetween(publicationYearDto.getFromPublicationYear(), publicationYearDto.getToPublicationYear());
    }

    public Long addBook(BookCreationDto bookCreationDto) {
        Book book = bookMapper.mapToBookFromCreationDto(bookCreationDto);
        bookDao.save(book);
        return book.getIsbn();
    }

    public void deleteBook(Long isbn) {
        if (bookDao.existsById(isbn)) {
            bookDao.deleteById(isbn);
        } else {
            throw new ResourceNotFoundException("isbn" + isbn);
        }
    }

    public Long editBook(BookEditDto bookEditDto) {
        Long isbn = bookEditDto.getIsbn();
        Book book = getBook(isbn);
        bookMapper.updateBookFromEditDto(book, bookEditDto);
        bookDao.save(book);
        return isbn;
    }

    public boolean isbnExists(Long isbn) {
        return bookDao.existsById(isbn);
    }

    public Long borrowBook(Long isbn) {
        Book book = getBook(isbn);
        if (book.getIsBorrowed())
            throw new BookIsAlreadyBorrowedException("isbn" + isbn);
        else {
            book.setIsBorrowed(true);
            return bookDao.save(book).getIsbn();
        }
    }

    public Long returnBook(Long isbn) {
        Book book = getBook(isbn);
        if (!book.getIsBorrowed())
            throw new BookIsNotBorrowedException("isbn" + isbn);
        else {
            book.setIsBorrowed(false);
            return bookDao.save(book).getIsbn();
        }
    }
}