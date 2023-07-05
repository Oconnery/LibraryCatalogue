package com.library.catalogue.exception.custom;

public class BookIsAlreadyBorrowedException extends RuntimeException {
    public BookIsAlreadyBorrowedException(String errorMessage) {
        this(errorMessage, null);
    }

    public BookIsAlreadyBorrowedException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
