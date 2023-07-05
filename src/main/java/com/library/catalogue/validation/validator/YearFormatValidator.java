package com.library.catalogue.validation.validator;

import com.library.catalogue.util.Util;
import com.library.catalogue.validation.constraint.ValidateYearFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class YearFormatValidator implements ConstraintValidator<ValidateYearFormat, Integer> {

    @Override
    public void initialize(ValidateYearFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        return Util.numberOfDigits(year).equals(4);
    }
}
