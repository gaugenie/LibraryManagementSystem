package com.library.bookstore.service;

import com.library.bookstore.execptions.RessourceNotFoundException;
import com.library.bookstore.repository.AuthorRepository;
import com.library.bookstore.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository TheAuthorRepository) {
        this.authorRepository = TheAuthorRepository;
    }

    // retrieve all Authors
    public Page<Author> getAllAuthor(Pageable page){
        return authorRepository.findAll(page);
    }

    // retrieve Author by id
    public Author getAuthorById(Long id) {
          Optional<Author> theAuthor = Optional.ofNullable(authorRepository.findById(id)
                  .orElseThrow(() -> new RessourceNotFoundException("Author with id " + id + " is not find ")));
        return theAuthor.get();
    }
    public Author createAuthor(Author TheAuthor){
        return authorRepository.save(TheAuthor);
    }
     public Author updateAuthor(Long id, Author TheAuthor){
         Author existingAuthor = getAuthorById(id);
         existingAuthor.setBiography(TheAuthor.getBiography() !=null ? TheAuthor.getBiography() : existingAuthor.getBiography());
         existingAuthor.setFirstName(TheAuthor.getFirstName() !=null ? TheAuthor.getFirstName() : existingAuthor.getFirstName());
         existingAuthor.setLastName(TheAuthor.getLastName() !=null ? TheAuthor.getLastName() : existingAuthor.getLastName());
         existingAuthor.setBirthDate(TheAuthor.getBirthDate() !=null ? TheAuthor.getBirthDate() : existingAuthor.getBirthDate());
         return authorRepository.save(TheAuthor);
     }

     public void deleteAuthor(Long id){
        Author existingUser = getAuthorById(id);
         authorRepository.delete(existingUser);
    }
}

