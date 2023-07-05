package com.library.catalogue.service;

import com.library.catalogue.dao.BookDao;
import com.library.catalogue.dto.inbound.BookCreationDto;
import com.library.catalogue.dto.inbound.BookEditDto;
import com.library.catalogue.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookDao bookDao;

    private Book testBook = new Book();

    @BeforeEach
    @AfterEach
    public void setTestBookFields() {
        testBook.setIsbn(1234567890L);
        testBook.setTitle("book-title");
        testBook.setAuthorFirstName("authFirstName");
        testBook.setAuthorLastName("authLastName");
        testBook.setPublicationYear(2000);
    }

    private BookCreationDto getBookCreationDto() {
        return BookCreationDto.builder()
                .isbn(1234567890L)
                .title("book-title")
                .authorFirstName("authFirstName")
                .authorLastName("authLastName")
                .publicationYear(2000)
                .build();
    }

    private BookEditDto getDifferentBookEditDto() {
        return BookEditDto.builder()
                .isbn(1234567890L)
                .authorFirstName("newFirstName")
                .authorLastName("newLastName")
                .build();
    }

    @Test
    void testGetBookThrowsExceptionWhenBookNotFound() {
        when(bookDao.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> bookService.getBook(9234567890L));
    }

    @Test
    void testGetBookReturnsBookWhenFound() {
        when(bookDao.findById(anyLong())).thenReturn(Optional.of(testBook));
        Book book = bookService.getBook(1234567890L);
        assertEquals(testBook, book);
    }

    @Test
    void testBookMapperMapsCreationDtoToModelCorrectly() {
        Book book = bookService.bookMapper.updateBookFromCreationDto(getBookCreationDto());
        assertEquals(testBook, book);
    }

    @Test
    void testBookMapperMapsOverModelCorrectly() {
        bookService.bookMapper.updateBookFromEditDto(testBook, getDifferentBookEditDto());

        Book alteredBookExpectation = new Book();

        alteredBookExpectation.setIsbn(1234567890L);
        alteredBookExpectation.setTitle("book-title");
        alteredBookExpectation.setAuthorFirstName("newFirstName");
        alteredBookExpectation.setAuthorLastName("newLastName");
        alteredBookExpectation.setPublicationYear(2000);

        assertEquals(alteredBookExpectation, testBook);
    }

    @Test
    void testDeleteBookThrowsExceptionWhenNotFound() {
        when(bookDao.existsById(anyLong())).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> bookService.deleteBook(9234567890L));
    }

    @Test
    void testEditBookThrowsExceptionWhenFindBookReturnsNull() {
        when(bookDao.findById(anyLong())).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> bookService.deleteBook(9234567890L));
    }
}