package com.library.catalogue.dto.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = AuthorDto.AuthorDtoBuilder.class)
public class AuthorDto {
    @JsonProperty
    @NotEmpty(message = "authorFirstName required.")
    private String authorFirstName;
    @JsonProperty
    @NotEmpty(message = "authorLastName required.")
    private String authorLastName;
}