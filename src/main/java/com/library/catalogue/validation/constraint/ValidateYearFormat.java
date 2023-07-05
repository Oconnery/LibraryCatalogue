package com.library.catalogue.validation.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = com.library.catalogue.validation.validator.YearFormatValidator.class)
@Documented
public @interface ValidateYearFormat {
    String message() default "Year is not in the valid format YYYY. For example: 1990 or 2031. Other formats are not allowed.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}