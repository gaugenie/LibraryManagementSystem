package com.library.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private String bookTitle;
    private String isbn;
    private String editor;
    private String type;
    private float price;
    private String bookDescription;
    private Long authorId;
}