package com.library.catalogue.exception.custom;

public class BookIsNotBorrowedException extends RuntimeException {
    public BookIsNotBorrowedException(String errorMessage) {
        this(errorMessage, null);
    }

    public BookIsNotBorrowedException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
