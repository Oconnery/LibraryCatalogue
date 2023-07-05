package com.library.catalogue.validation.constraint;

import com.library.catalogue.validation.validator.IsbnDoesNotExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsbnDoesNotExistValidator.class)
@Documented
public @interface ValidateIsbnDoesNotExistAlready {
    String message() default "Isbn already exists or is null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
