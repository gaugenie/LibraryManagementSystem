package com.library.bookstore.controller;

import com.library.bookstore.constants.BookConstants;
import com.library.bookstore.dto.ResponseDto;
import com.library.bookstore.dto.AuthorDto;
import com.library.bookstore.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@AllArgsConstructor
public class AuthorRestController {
    @Autowired
    private  AuthorService authorService;

    @PostMapping
    public ResponseEntity<ResponseDto> createAuthor(@RequestBody AuthorDto authorDto){
        authorService.createAuthor(authorDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(BookConstants.STATUS_201, BookConstants.MESSAGE_201));
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAutthors(){
        return new ResponseEntity<>(authorService.getAllAuthor(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("id") Long id){
        AuthorDto authorDto = authorService.getAuthorById(id);
        return new ResponseEntity<>( authorDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> createAuthor(@PathVariable("id") Long id,
                                                    @RequestBody AuthorDto authorDto){
      AuthorDto authorDto1 =  authorService.updateAuthor(authorDto, id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(BookConstants.STATUS_201, BookConstants.MESSAGE_201));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteAuthor(@PathVariable("id") Long id){
        authorService.deleteAuthorById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(BookConstants.STATUS_200, BookConstants.MESSAGE_200));
    }

}
