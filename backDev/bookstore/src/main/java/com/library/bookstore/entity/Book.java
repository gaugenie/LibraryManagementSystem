package com.library.bookstore.entity;

import jakarta.persistence.*;
// TODO: 02/01/2024  change the import * to specific import
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String bookName;
    @Column(name="isbn_number")
    private String isbn;
    @Column(nullable = false)
    private LocalDate datePublication;

    @Column(nullable = false)
    private String editor;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id", referencedColumnName = "id", nullable = false)
    private Author author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}