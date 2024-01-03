package com.library.bookstore.dto;

import lombok.*;

import java.util.Set;


@Getter
@Setter
public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String biography;
    private String email;
    private Set<BookDto> books;
}
