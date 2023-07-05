package com.library.catalogue.dto.inbound;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDto {
    @NotEmpty(message = "author first name required.")
    private String authorFirstName;
    @NotEmpty(message = "author last name required.")
    private String authorLastName;
}