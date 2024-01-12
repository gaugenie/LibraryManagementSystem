package com.library.bookstore.mapper;

import com.library.bookstore.dto.AuthorDto;
import com.library.bookstore.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorAutoMapper {
    AuthorAutoMapper MAPPER = Mappers.getMapper(AuthorAutoMapper.class);
    AuthorDto mapToAuthorDto(Author author);
    Author mapToAuthor(AuthorDto authorDto);
}

// todo : faire un interface BookAutoMapper et AuthorAutoMapper a part
// todo: faire une injection de mapstruct component model
