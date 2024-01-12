package com.library.bookstore.mapper;

import com.library.bookstore.dto.BookDto;
import com.library.bookstore.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookAutoMapper {
    BookAutoMapper MAPPER = Mappers.getMapper(BookAutoMapper.class);
    BookDto mapToBookDto(Book book);
    Book mapToBook(BookDto bookDto);
}

// todo : faire un interface BookAutoMapper et AuthorAutoMapper a part
// todo: faire une injection de mapstruct component model
