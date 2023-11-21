package com.library.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookTitle;
    @Column(name="isbn_number")
    private String isbn;
    private Date datePublication;
    private String editor;
    private String type;
    private float price;
    private String descritpion;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}
