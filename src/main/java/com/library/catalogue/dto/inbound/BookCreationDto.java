package com.library.catalogue.dto.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.library.catalogue.validation.constraint.ValidateIsbnDoesNotExistAlready;
import com.library.catalogue.validation.constraint.ValidateIsbnFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = BookCreationDto.BookCreationDtoBuilder.class)
public class BookCreationDto {
    @ValidateIsbnFormat
    @ValidateIsbnDoesNotExistAlready
    @JsonProperty
    private Long isbn;
    @JsonProperty
    @NotEmpty(message = "title required")
    private String title;
    @JsonProperty
    @NotEmpty(message = "author first name required")
    private String authorFirstName;
    @JsonProperty
    @NotEmpty(message = "author last name required")
    private String authorLastName;
    @JsonProperty
    private Integer publicationYear;
    @JsonProperty
    private Boolean isBorrowed;
}
