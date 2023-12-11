package com.library.bookstore.dto;

import com.library.bookstore.entity.Author;
import lombok.Data;

import java.util.Date;

@Data
public class BookDto {
    private String bookTitle;
    private String isbn;
    private Date datePublication;
    private String editor;
    private String type;
    private float price;
    private String descritpion;
    private Author author;

}
