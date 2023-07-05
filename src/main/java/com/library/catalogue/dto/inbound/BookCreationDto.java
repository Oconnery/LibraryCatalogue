package com.library.catalogue.dto.inbound;

import com.library.catalogue.validation.constraint.ValidateIsbnDoesNotExistAlready;
import com.library.catalogue.validation.constraint.ValidateIsbnFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookCreationDto {
    @ValidateIsbnFormat
    @ValidateIsbnDoesNotExistAlready
    private Long isbn;
    @NotEmpty(message = "title required")
    private String title;
    @NotEmpty(message = "author first name required")
    private String authorFirstName;
    @NotEmpty(message = "author last name required")
    private String authorLastName;
    private Integer publicationYear;
    private Boolean isBorrowed;
}
