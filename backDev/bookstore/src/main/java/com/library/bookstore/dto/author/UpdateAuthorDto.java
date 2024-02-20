package com.library.bookstore.dto.author;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateAuthorDto {

    private Long id;

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


