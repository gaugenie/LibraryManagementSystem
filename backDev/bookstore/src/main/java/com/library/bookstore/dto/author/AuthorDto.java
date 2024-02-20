package com.library.bookstore.dto.author;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class AuthorDto {

    @NotBlank(message = "bookName should have 2 minimum characters ")
    private String firstName;

    @NotBlank(message = "bookName should have 2 minimum characters ")
    private String lastName;

    @NotNull
    private LocalDate birthDate;

    @NotBlank(message = "bookName should have 2 minimum characters ")
    @Size(min = 10, message = "bookName should have 10 minimum characters ")
    private String biography;

    @Email
    private String email;
}

// Todo creation d'un DTO update , create, ex: CreateAuthorDto , UpdateAuthorDto, AuthorDetailsDto
// Todo creation d'un DTO update , create, ex: CreateBookDto , UpdateBookDto, BookDetailsDto

