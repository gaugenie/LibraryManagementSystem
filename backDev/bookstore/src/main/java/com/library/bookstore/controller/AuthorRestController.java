package com.library.bookstore.controller;

import com.library.bookstore.entity.Author;
import com.library.bookstore.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthorRestController {
    private AuthorService authorService;

    public AuthorRestController(AuthorService TheAuthorService) {
        this.authorService = TheAuthorService;
    }
    @GetMapping("/authors")
    public Page<Author> getAllAuthor(Pageable page){
        return authorService.getAllAuthor(page);
    }
    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Long id){
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }
    @PostMapping("/authors")
    public ResponseEntity<Author> saveAuthor(@Valid @RequestBody Author theAuthor){
        return new ResponseEntity<Author>(authorService.createAuthor(theAuthor), HttpStatus.CREATED);
    }
    @PutMapping("/authors/{id}")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author theAuthor, @PathVariable("id") Long id){
        return new ResponseEntity<Author> (authorService.updateAuthor(id, theAuthor), HttpStatus.OK);
    }
    @DeleteMapping("/authors/{id}")
    public ResponseEntity<HttpStatus>deleteAuthorById(@PathVariable("id") Long id){
        authorService.deleteAuthor(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
