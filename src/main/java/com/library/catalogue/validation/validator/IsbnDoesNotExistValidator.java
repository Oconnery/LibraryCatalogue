package com.library.catalogue.validation.validator;

import com.library.catalogue.service.BookService;
import com.library.catalogue.validation.constraint.ValidateIsbnDoesNotExistAlready;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class IsbnDoesNotExistValidator implements ConstraintValidator<ValidateIsbnDoesNotExistAlready, Long> {

    @Autowired
    BookService bookService;
    
    @Override
    public boolean isValid(Long isbn, ConstraintValidatorContext context) {
        if (isbn == null)
            return true;
        else return !bookService.isbnExists(isbn);
    }
}
