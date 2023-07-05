package com.library.catalogue.exception;

import com.library.catalogue.exception.custom.BookIsAlreadyBorrowedException;
import com.library.catalogue.exception.custom.BookIsNotBorrowedException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private LinkedHashMap<String, String> generateResponseBody(String message) {
        LinkedHashMap<String, String> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("message", message);
        return body;
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundExceptions(RuntimeException exception, WebRequest request) {
        LinkedHashMap<String, String> body = generateResponseBody("The resource: '" + exception.getMessage() + "'  was not found in the database");
        return handleExceptionInternal(exception, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {BookIsNotBorrowedException.class})
    protected ResponseEntity<Object> handleBookIsNotBorrowedExceptions(RuntimeException exception, WebRequest request) {
        LinkedHashMap<String, String> body = generateResponseBody("The book of isbn: '" + exception.getMessage() + "' is not borrowed, so cannot be returned.");
        return handleExceptionInternal(exception, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {BookIsAlreadyBorrowedException.class})
    protected ResponseEntity<Object> handBookIsAlreadyBorrowedExceptions(RuntimeException exception, WebRequest request) {
        LinkedHashMap<String, String> body = generateResponseBody("The book of isbn: '" + exception.getMessage() + "' is not borrowed, so cannot be returned.");
        return handleExceptionInternal(exception, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}