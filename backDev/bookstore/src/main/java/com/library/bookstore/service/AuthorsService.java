package com.library.bookstore.service;

import com.library.bookstore.entity.Authors;

import java.util.List;

public interface AuthorsService {
    List<Authors> findAll();
    Authors findById(int theId);
    Authors save(Authors theEmployee);
    void deleteById(int theId);
}
