package com.library.catalogue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Book {

    @Id
    private Long isbn;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private Integer publicationYear;
}