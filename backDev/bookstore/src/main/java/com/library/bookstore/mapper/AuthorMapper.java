package com.library.bookstore.mapper;

import com.library.bookstore.dto.AuthorDto;
import com.library.bookstore.entity.Author;
import lombok.Builder;

@Builder
public class AuthorMapper {

    public static AuthorDto mapToAuthorDto(Author author, AuthorDto authorDto) {
        authorDto.setAuthorBiography(author.getBiography());
        authorDto.setLastName(author.getLastName());
        authorDto.setFirstName(author.getFirstName());
        authorDto.setEmail(author.getEmail());
        return authorDto;
    }

    public static Author mapToAuthor( AuthorDto authorDto, Author author) {
        author.setBiography(authorDto.getAuthorBiography());
        author.setLastName(authorDto.getLastName());
        author.setFirstName(authorDto.getFirstName());
        author.setEmail(authorDto.getEmail());
        return author;
    }
}
