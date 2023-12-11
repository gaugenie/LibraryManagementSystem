package com.library.bookstore.controller;

import com.library.bookstore.entity.Author;
import com.library.bookstore.entity.dto.AuthorDto;
import com.library.bookstore.execptions.RessourceNotFoundException;
import com.library.bookstore.service.AuthorService;
import com.library.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorRestController {
    @Autowired
    private final AuthorService authorService;
    @Autowired
    private final BookService bookService;
    @Autowired
    public AuthorRestController(AuthorService TheAuthorService, BookService theBookService) {
        this.authorService = TheAuthorService;
        this.bookService = theBookService;
    }
    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthor(){
        List<Author> authors = authorService.getAllAuthors();
        List<AuthorDto> authorDtos = authors.stream().map(AuthorDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(authorDtos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable final Long id){
        Author author = authorService.getAuthor(id);
        return new ResponseEntity<>(AuthorDto.from(author), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody final AuthorDto authorDto){
        Author author = authorService.addAuthor(Author.from(authorDto));
        return new ResponseEntity<>(AuthorDto.from(author), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@RequestBody Author theAuthor, @PathVariable Long id){
        try {
            authorService.editAuthor(id, theAuthor);
            return new ResponseEntity<> ("Update author with id " +id, HttpStatus.OK);
        } catch (RessourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteAuthorById(@PathVariable Long id){
        try {
            authorService.deleteAuthor(id);
            return new ResponseEntity<>("Successfully Author deleted with id " +id ,HttpStatus.OK);
        } catch (RessourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(value="/{cardId}/books/{bookItems}/add")
    public ResponseEntity<AuthorDto> addBookToAuthor(@PathVariable final Long authorId,
                                                     @PathVariable final Long bookid){
        Author author = authorService.addBookToAuthor(authorId, bookid );
        return new ResponseEntity<>(AuthorDto.from(author), HttpStatus.OK);
    }
    @DeleteMapping(value="/{cardId}/books/{bookItems}/add")
    public ResponseEntity<AuthorDto> removeBookToAuthor(@PathVariable final Long authorId,
                                                        @PathVariable final Long bookid){
        Author author = authorService.removeBookFromAuthor(authorId, bookid );
        return new ResponseEntity<>(AuthorDto.from(author), HttpStatus.OK);
    }

}
