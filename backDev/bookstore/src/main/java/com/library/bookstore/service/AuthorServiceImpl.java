package com.library.bookstore.service;

import com.library.bookstore.repository.AuthorRepository;
import com.library.bookstore.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorsRepository;

    public AuthorServiceImpl(AuthorRepository TheAuthorsRepository) {
        this.authorsRepository = TheAuthorsRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorsRepository.findAll();
    }

    @Override
    public Author findById(int theId) {
        Optional<Author> result = authorsRepository.findById(theId);
        Author theAuthors = null;

        if(result.isPresent()){
            theAuthors = result.get();
        }else {
            // we didn't find the employee
            throw new RuntimeException("" + theId);

        }
        return theAuthors;
    }

    @Override
    public Author save(Author theEmployee) {
        return authorsRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        authorsRepository.deleteById(theId);
    }
}
