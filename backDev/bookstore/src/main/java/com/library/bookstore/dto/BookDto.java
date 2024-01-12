package com.library.bookstore.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class BookDto {
    // todo mettre des validations sur les champs obligatoires

    @NotBlank
    @Size(min= 2, message = "bookName should have 2 minimum characters ")
    private String bookName;

    @NotBlank
    @Size(min= 2, message = "isbn should have 2 minimum characters ")
    private String isbn;

    @NotNull
    private LocalDate datePublication;

    @NotBlank
    @Size(min= 5, message = "editor should have 5 minimum characters ")
    private String editor;

    @NotBlank
    @Size(min= 2, message = "type should have 2 minimum characters ")
    private String type;

    @NotNull
    @Min(value = 0)
    private float price;

    @NotBlank
    private String description;
}
