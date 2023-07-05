package com.library.catalogue.dto.inbound;

import com.library.catalogue.validation.constraint.ValidateIsbnFormat;
import com.library.catalogue.validation.constraint.ValidateYearFormat;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookEditDto {
    @ValidateIsbnFormat
    private Long isbn;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    @ValidateYearFormat
    private Integer publicationYear;
    private Boolean isBorrowed;
}
