package com.library.bookstore.entity;

import jakarta.persistence.*;
// TODO: 02/01/2024  change the import * to specific import
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private String datePublication;
    // TODO: 02/01/2024 change String de datePublication for Localdatime
    @Column(nullable = false)
    private String editor;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String price;
    // TODO: 02/01/2024  change String of price to Bigdecimal
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