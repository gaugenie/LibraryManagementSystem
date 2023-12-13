package com.library.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="book")
public class Book extends BaseEntity {
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id", referencedColumnName = "id")
    private Author author;
}