package com.library.catalogue.validation.constraint;

import com.library.catalogue.validation.validator.IsbnFormatValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsbnFormatValidator.class)
@Documented
public @interface ValidateIsbnFormat {
    String message() default "Isbn format is not valid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
