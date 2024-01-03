package com.library.bookstore.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookDto {
    // todo mettre des validations sur les champs obligatoires
    private String bookName;
    private String isbn;
    private String datePublication;
    private String editor;
    private String type;
    private String price;
    private String description;
}