package com.library.catalogue.dto.inbound;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;

@Builder
@Data
public class BookEditDto {

    @NonNull
    private Long isbn;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private Integer publicationYear;
}
