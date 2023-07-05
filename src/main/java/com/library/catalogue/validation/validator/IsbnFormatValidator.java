package com.library.catalogue.validation.validator;

import com.library.catalogue.util.Util;
import com.library.catalogue.validation.constraint.ValidateIsbnFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class IsbnFormatValidator implements ConstraintValidator<ValidateIsbnFormat, Long> {

    List<Integer> suitableIsbnSize = Arrays.asList(10, 13);

    @Override
    public void initialize(ValidateIsbnFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long isbn, ConstraintValidatorContext context) {
        if (isbn == null) {
            return false;
        } else {
            Integer length = Util.numberOfDigits(isbn);
            return suitableIsbnSize.stream().anyMatch(x -> x.equals(length));
        }
    }
}
