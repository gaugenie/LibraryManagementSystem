package com.library.bookstore.controller;

import com.library.bookstore.entity.Author;
import com.library.bookstore.execptions.RessourceNotFoundException;
import com.library.bookstore.service.AuthorService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorRestController {
    private AuthorService authorService;

    public AuthorRestController(AuthorService TheAuthorService) {
        this.authorService = TheAuthorService;
    }
    @GetMapping
    public List<Author> getAllAuthor(Pageable page){
        return authorService.getAllAuthor(page).toList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }
    @PostMapping("createAuthor")
    public ResponseEntity<Author> createAuthor(@Valid @RequestBody Author theAuthor){
        return new ResponseEntity<Author>(authorService.createAuthor(theAuthor), HttpStatus.CREATED);
    }
    @PutMapping("updateAuthor/{id}")
    public ResponseEntity<?> updateAuthor(@RequestBody Author theAuthor, @PathVariable Long id){
        try {
            authorService.updateAuthor(id, theAuthor);
            return new ResponseEntity<> ("Update author with id " +id, HttpStatus.OK);
        } catch (RessourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("deleteAuthor/{id}")
    public ResponseEntity<?>deleteAuthorById(@PathVariable Long id){
        try {
            authorService.deleteAuthor(id);
            return new ResponseEntity<>("Successfully Author deleted with id " +id ,HttpStatus.OK);
        } catch (RessourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
