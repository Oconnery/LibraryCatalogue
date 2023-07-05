package com.library.catalogue.web.controller;

import com.library.catalogue.validation.validator.IsbnDoesNotExistValidator;
import com.library.catalogue.validation.validator.IsbnFormatValidator;
import com.library.catalogue.validation.validator.YearFormatValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class BookValidatorsTest {

    IsbnFormatValidator isbnFormatValidator = new IsbnFormatValidator();
    YearFormatValidator yearFormatValidator = new YearFormatValidator();
    IsbnDoesNotExistValidator isbnDoesNotExistValidator = new IsbnDoesNotExistValidator();

    @Test
    void testIsbnFormatValidatorInvalidatesBadSize() {
        assertFalse(isbnFormatValidator.isValid(123L, null));
    }

    @Test
    void testIsbnFormatValidatorValidatesGoodSize() {
        assertTrue(isbnFormatValidator.isValid(1234567890123L, null));
    }

    @Test
    void testYearFormatValidatorInvalidatesBadFormat() {
        assertFalse(yearFormatValidator.isValid(0, null));
    }

    @Test
    void testYearFormatValidatorInvalidatesNullFormat() {
        assertFalse(yearFormatValidator.isValid(0, null));
    }

    @Test
    void testYearFormatValidatorValidatesGoodFormat() {
        assertTrue(yearFormatValidator.isValid(2000, null));
    }

    @Test
    void testIsbnDoesNotExistValidatorValidatesNull() {
        assertTrue(isbnDoesNotExistValidator.isValid(null, null));
    }
}