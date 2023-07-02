package com.library.catalogue.dto.inbound;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class AuthorDto {
    @NonNull
    private String authorFirstName;
    @NonNull
    private String authorLastName;
}