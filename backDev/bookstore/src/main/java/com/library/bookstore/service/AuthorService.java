package com.library.bookstore.service;

import com.library.bookstore.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    Author findById(int theId);
    Author save(Author theEmployee);
    void deleteById(int theId);
}
