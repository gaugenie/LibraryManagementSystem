package com.library.bookstore.dto.author;

import com.library.bookstore.dto.BookDto;
import com.opencsv.bean.CsvBindByName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDetailsDto {


    private String firstName;


    private String lastName;

    private LocalDate birthDate;

    private String biography;

    private String email;

    private Set<BookDto> books;
}
