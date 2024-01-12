package com.library.bookstore.controller;

import com.library.bookstore.dto.AuthorDto;
import com.library.bookstore.service.AuthorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@AllArgsConstructor
public class AuthorRestController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto authorDto){
        AuthorDto createdAuthor =  authorService.createAuthor(authorDto);
        return new ResponseEntity<>(createdAuthor,HttpStatus.CREATED);
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
    public ResponseEntity<AuthorDto> createAuthor(@PathVariable("id") Long id,
                                                    @Valid @RequestBody AuthorDto authorDto){
      AuthorDto authorDto1 =  authorService.updateAuthor(authorDto, id);
        return new ResponseEntity<>(authorDto1,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") Long id){
        authorService.deleteAuthorById(id);
        return new ResponseEntity<>("Author deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/upload-authors-data")
    public ResponseEntity<String> uploadAuthorsData(@RequestParam("file") MultipartFile file){
        authorService.saveAuthorToDatabase(file);

        return new ResponseEntity<>("Author Upload successfully", HttpStatus.CREATED);
    }
}
