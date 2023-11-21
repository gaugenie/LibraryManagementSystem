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

     public void updateAuthor(Long id, Author TheAuthor) throws RessourceNotFoundException {
        Optional<Author> authorWithId = authorRepository.findById(id);
        if(authorWithId.isPresent()){
            Author authorToSave = authorWithId.get();
            authorToSave.setBiography(TheAuthor.getBiography() !=null ? TheAuthor.getBiography() : authorToSave.getBiography());
            authorToSave.setFirstName(TheAuthor.getFirstName() !=null ? TheAuthor.getFirstName() : authorToSave.getFirstName());
            authorToSave.setLastName(TheAuthor.getLastName() !=null ? TheAuthor.getLastName() : authorToSave.getLastName());
            authorToSave.setBirthDate(TheAuthor.getBirthDate() !=null ? TheAuthor.getBirthDate() : authorToSave.getBirthDate());
            authorRepository.save(authorToSave);
        }
     }

     public void deleteAuthor(Long id) throws RessourceNotFoundException{
         Optional<Author> authorWithId = authorRepository.findById(id);
         if(!authorWithId.isPresent()){
             throw new RessourceNotFoundException("the Author with the id " +id+ " is not found");
         }else {
             authorRepository.deleteById(id);
         }
    }
}

