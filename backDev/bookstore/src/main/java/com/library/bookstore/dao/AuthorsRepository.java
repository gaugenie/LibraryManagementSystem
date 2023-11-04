package com.library.bookstore.dao;

import com.library.bookstore.entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepository extends JpaRepository<Authors, Integer> {
}
