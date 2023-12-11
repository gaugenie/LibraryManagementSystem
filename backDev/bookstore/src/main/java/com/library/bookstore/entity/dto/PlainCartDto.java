package com.library.bookstore.entity.dto;

import com.library.bookstore.entity.Author;
import lombok.Data;

import java.util.Date;

@Data
public class PlainCartDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String biography;

   public static PlainCartDto from(Author author){
        PlainCartDto plainCartDto = new PlainCartDto();
        plainCartDto.setId(author.getId());
        plainCartDto.setFirstName(author.getFirstName());
        plainCartDto.setLastName(author.getLastName());
        plainCartDto.setBirthDate(author.getBirthDate());
        plainCartDto.setBiography(author.getBiography());
        return plainCartDto;
    }
}
