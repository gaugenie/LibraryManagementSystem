package com.library.bookstore.dto.author;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CreateAuthorDto {

    @NotBlank(message = "bookName should have 2 minimum characters ")
    @CsvBindByName(column = "firstname")
    private String firstName;

    @NotBlank(message = "bookName should have 2 minimum characters ")
    @CsvBindByName(column = "lastname")
    private String lastName;

    @NotNull
    @CsvBindByName(column = "birthdate")
    @CsvDate(value = "yyyy-MM-dd") // yyyy-MM-dd yyyy-MM-dd'T'HH:mm:ss.SSSZ yyyy-MM-dd'T'HH:mm:ss.SSSXXX yyyy-MM-dd'
    private LocalDate birthDate;

    @NotBlank(message = "bookName should have 2 minimum characters ")
    @Size(min = 10, message = "bookName should have 10 minimum characters ")
    @CsvBindByName(column = "biography")
    private String biography;

    @Email
    @CsvBindByName(column = "email")
    private String email;
}


