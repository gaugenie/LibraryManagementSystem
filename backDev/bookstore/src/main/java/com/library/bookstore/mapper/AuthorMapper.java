package com.library.bookstore.mapper;

import com.library.bookstore.dto.author.AuthorDto;
import com.library.bookstore.dto.author.AuthorDetailsDto;
import com.library.bookstore.dto.author.CreateAuthorDto;
import com.library.bookstore.dto.author.UpdateAuthorDto;
import com.library.bookstore.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto mapToAuthorDto(Author author);
    AuthorDetailsDto mapToAuthorDetailsDto(Author author);
    @Mapping(target = "id", ignore = true)
    void updateAuthor(UpdateAuthorDto updateAuthorDto, @MappingTarget Author author);
    Author mapToAuthor(CreateAuthorDto createAuthorDto);
}
