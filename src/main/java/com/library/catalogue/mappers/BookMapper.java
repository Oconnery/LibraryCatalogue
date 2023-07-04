package com.library.catalogue.mappers;

import com.library.catalogue.dto.inbound.BookEditDto;
import com.library.catalogue.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {

    void updateBookFromEditDto(@MappingTarget Book book, BookEditDto bookEditDto);
}