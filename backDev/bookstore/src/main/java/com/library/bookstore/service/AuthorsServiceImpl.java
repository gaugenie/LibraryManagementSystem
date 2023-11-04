package com.library.bookstore.service;

import com.library.bookstore.dao.AuthorsRepository;
import com.library.bookstore.entity.Authors;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorsServiceImpl implements AuthorsService{

    private AuthorsRepository authorsRepository;

    public AuthorsServiceImpl(AuthorsRepository TheAuthorsRepository) {
        this.authorsRepository = TheAuthorsRepository;
    }

    @Override
    public List<Authors> findAll() {
        return authorsRepository.findAll();
    }

    @Override
    public Authors findById(int theId) {
        Optional<Authors> result = authorsRepository.findById(theId);
        Authors theAuthors = null;

        if(result.isPresent()){
            theAuthors = result.get();
        }else {
            // we didn't find the employee
            throw new RuntimeException("" + theId);

        }
        return theAuthors;
    }

    @Override
    public Authors save(Authors theEmployee) {
        return authorsRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        authorsRepository.deleteById(theId);
    }
}
