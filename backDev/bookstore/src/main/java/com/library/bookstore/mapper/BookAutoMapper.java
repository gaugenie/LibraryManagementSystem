package com.library.bookstore.mapper;

import com.library.bookstore.dto.BookDto;
import com.library.bookstore.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
//todo rename BookAutoMapper to BookMapper
public interface BookAutoMapper {
    BookDto mapToBookDto(Book book);
    Book mapToBook(BookDto bookDto);
}

