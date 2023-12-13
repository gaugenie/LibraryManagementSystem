package com.library.bookstore.controller;

import com.library.bookstore.constants.BookConstants;
import com.library.bookstore.dto.BookDto;
import com.library.bookstore.dto.ResponseDto;
import com.library.bookstore.entity.Author;
import com.library.bookstore.dto.AuthorDto;
import com.library.bookstore.entity.Book;
import com.library.bookstore.execptions.RessourceNotFoundException;
import com.library.bookstore.service.AuthorService;
import com.library.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/author")
@AllArgsConstructor
public class AuthorRestController {
    @Autowired
    private  AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createSimpleAuthor(@RequestBody AuthorDto authorDto){
        authorService.createAuthor(authorDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(BookConstants.STATUS_201, BookConstants.MESSAGE_201));
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAutthors(){
        List<Author> authors = authorService.getAllAuthor();
        return new ResponseEntity<>( authors , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
        Author author = authorService.getAuthorById(id);
        return new ResponseEntity<>( author, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteAuthor(@PathVariable Long id){
        Author author = authorService.deleteAuthor(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(BookConstants.STATUS_200, BookConstants.MESSAGE_200));
    }

    @PostMapping("/{authorId}/books/{bookId}/add")
    public ResponseEntity<ResponseDto> addBookToAuthor( @PathVariable Long authorId,
                                                        @PathVariable Long bookId){
        authorService.addBookToAuthor(authorId, bookId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(BookConstants.STATUS_201, BookConstants.MESSAGE_201));
    }

    @DeleteMapping("/{authorId}/books/{bookId}/delete")
    public ResponseEntity<ResponseDto> deleteBookToAuthor( @PathVariable Long authorId,
                                                           @PathVariable Long bookId){
        authorService.removeBookFromAuthor(authorId, bookId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(BookConstants.STATUS_201, BookConstants.MESSAGE_201));
    }

}
