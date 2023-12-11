package com.library.bookstore.entity.dto;

import com.library.bookstore.entity.Book;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
//TODO : creer plusieur DTO , la creation , update, ex: CreateBookDto, UpdateBookDto, BookDetailsDto
public class BookDto {
    private Long id;
    private String bookName;
    private String isbn;
    // TODO: LocalDate
    private Date datePublication;
    private String category;
    private float price;
    private String descritpion;
    private Long authorId;

    // TODO: enlever tous les static

    public static BookDto from(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setBookName(book.getBookName());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setPrice(book.getPrice());
        bookDto.setDescritpion(book.getDescritpion());
        bookDto.setCategory(book.getCategory());
        bookDto.setDatePublication(book.getDatePublication());
        bookDto.setPrice(book.getPrice());

        return bookDto;
    }
}
