package com.library.bookstore.entity;

import com.library.bookstore.entity.dto.AuthorDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="author")
//TODO: Renomme class avec entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String biography;

    // TODO: fetch type lazy
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "author_id")
    private List<Book> books = new ArrayList<>();

    //TODO : reimplement
    public void addBook(Book book){
        books.add(book);
    }
    public void removeBook(Book book){
        books.remove(book);
    }

    //TODO: deplacer dans un mapper
    public static Author from(AuthorDto authorDto){
        Author author = new Author();
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setBirthDate(authorDto.getBirthDate());
        author.setBiography(authorDto.getBiography());
        return author;
    }
}
