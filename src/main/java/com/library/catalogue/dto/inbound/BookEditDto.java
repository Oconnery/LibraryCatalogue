package com.library.catalogue.dto.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.library.catalogue.validation.constraint.ValidateIsbnFormat;
import com.library.catalogue.validation.constraint.ValidateYearFormat;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = BookEditDto.BookEditDtoBuilder.class)
public class BookEditDto {
    @ValidateIsbnFormat
    @JsonProperty
    private Long isbn;
    @JsonProperty
    private String title;
    @JsonProperty
    private String authorFirstName;
    @JsonProperty
    private String authorLastName;
    @ValidateYearFormat
    @JsonProperty
    private Integer publicationYear;
    @JsonProperty
    private Boolean isBorrowed;
}
