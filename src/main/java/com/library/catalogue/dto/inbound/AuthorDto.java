package com.library.catalogue.dto.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = PublicationYearDto.PublicationYearDtoBuilder.class)
public class AuthorDto {
    @JsonProperty
    @NotEmpty(message = "author first name required.")
    private String authorFirstName;
    @JsonProperty
    @NotEmpty(message = "author last name required.")
    private String authorLastName;
}