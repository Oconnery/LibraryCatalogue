package com.library.catalogue.dao;

import com.library.catalogue.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book, Long> {
    List<Book> findByAuthorFirstNameAndAuthorLastName(String authorFirstName, String authorLastName);

    List<Book> findByPublicationYearBetween(Integer fromPublicationYear, Integer toPublicationYear);
}