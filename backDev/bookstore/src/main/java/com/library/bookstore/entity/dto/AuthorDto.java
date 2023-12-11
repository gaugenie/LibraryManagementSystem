package com.library.bookstore.entity.dto;

import com.library.bookstore.entity.Author;
import com.library.bookstore.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//TODO : creer plusieur DTO , la creation , update, ex: CreateAuthorDto, UpdateAuthorDto, AuthorDetailsDto
public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String biography;
    private List<BookDto> booksDto = new ArrayList<>();

    //TODO: fluent setter
    public static AuthorDto from(Author author) {
        AuthorDto authorDto = new AuthorDtoBuilder()
                .firstName(author.getFirstName())
                .lastName((author.getLastName()))
                .build();
        authorDto.setId(author.getId());
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());
        authorDto.setBirthDate(author.getBirthDate());
        authorDto.setBiography(author.getBiography());
        authorDto.setBooksDto(author.getBooks().stream().map(BookDto::from).collect(Collectors.toList()));
        return authorDto;
    }
}
