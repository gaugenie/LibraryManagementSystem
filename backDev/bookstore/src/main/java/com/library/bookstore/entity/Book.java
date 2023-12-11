package com.library.bookstore.entity;

import com.library.bookstore.entity.dto.BookDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bookName;
    @Column(name="isbn_number")
    private String isbn;
    private Date datePublication;
    private String category;
    private float price;
    private String descritpion;

    @ManyToOne
    private Author author;

    public static Book from(BookDto bookDto){
        Book book = new Book();
        book.setBookName(bookDto.getBookName());
        book.setIsbn(bookDto.getIsbn());
        book.setPrice(bookDto.getPrice());
        book.setDescritpion(bookDto.getDescritpion());
        book.setCategory(bookDto.getCategory());
        book.setDatePublication(bookDto.getDatePublication());
        book.setPrice(bookDto.getPrice());
        return book;
    }

}
