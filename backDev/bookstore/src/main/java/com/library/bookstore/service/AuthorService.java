package com.library.bookstore.service;

import com.library.bookstore.execptions.RessourceNotFoundException;
import com.library.bookstore.repository.AuthorRepository;
import com.library.bookstore.entity.Author;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository TheAuthorRepository) {
        this.authorRepository = TheAuthorRepository;
    }

    // retrieve Author by id
    public Author getAuthor(Long authorId) {
        Optional<Author> theAuthor = Optional.ofNullable(authorRepository.findById(authorId)
                .orElseThrow(() -> new RessourceNotFoundException("Author with id " + authorId + " is not find ")));
        return theAuthor.get();
    }
    // retrieve Author by id
    public List<Author> getAllAuthors(){
        List<Author> authors = StreamSupport
                .stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return authors;
    }

    public Author getAuthorById(Long authorId) {
        Author theAuthor = getAuthor(authorId);
        return theAuthor;
    }

    public Author createAuthor(Author theAuthor){
        Author Author = new Author();
        Author.setFirstName(theAuthor.getFirstName());
        Author.setLastName(theAuthor.getLastName());
        Author.setBirthDate(theAuthor.getBirthDate());
        Author.setBirthDate(theAuthor.getBirthDate());
        return authorRepository.save(theAuthor);
    }

    @Transactional
     public void updateAuthor(Long id, Author TheAuthor) throws RessourceNotFoundException {
        Optional<Author> authorWithId = authorRepository.findById(id);
        if(authorWithId.isPresent()){
            Author authorToEdit = authorWithId.get();
            authorToEdit.setBiography(TheAuthor.getBiography() !=null ? TheAuthor.getBiography() : authorToEdit.getBiography());
            authorToEdit.setFirstName(TheAuthor.getFirstName() !=null ? TheAuthor.getFirstName() : authorToEdit.getFirstName());
            authorToEdit.setLastName(TheAuthor.getLastName() !=null ? TheAuthor.getLastName() : authorToEdit.getLastName());
            authorToEdit.setBirthDate(TheAuthor.getBirthDate() !=null ? TheAuthor.getBirthDate() : authorToEdit.getBirthDate());
            authorRepository.save(authorToEdit);
        }
     }

     public void deleteAuthor(Long id) throws RessourceNotFoundException{
         Optional<Author> authorWithId = authorRepository.findById(id);
         if(!authorWithId.isPresent()){
             throw new RessourceNotFoundException("the Book with the id " +id+ " is not found");
         }else {
             authorRepository.deleteById(id);
         }
     }
}

