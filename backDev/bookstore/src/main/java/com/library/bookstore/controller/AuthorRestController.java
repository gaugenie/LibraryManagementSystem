package com.library.bookstore.controller;

import com.library.bookstore.dto.author.AuthorDto;
import com.library.bookstore.dto.author.AuthorDetailsDto;
import com.library.bookstore.dto.author.CreateAuthorDto;
import com.library.bookstore.dto.author.UpdateAuthorDto;
import com.library.bookstore.entity.Author;
import com.library.bookstore.mapper.AuthorMapper;
import com.library.bookstore.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorRestController {

    private final AuthorService authorService;
    private final com.library.bookstore.utils.CSVParser csvParser;

    @PostMapping
    public ResponseEntity<AuthorDetailsDto> saveAuthor(@Valid @RequestBody CreateAuthorDto createAuthorDto){
        AuthorDetailsDto createdAuthor =  authorService.saveAuthor(createAuthorDto);
        return new ResponseEntity<>(createdAuthor,HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/xml")
    public ResponseEntity<List<AuthorDto>> getAllAutthors(){
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public AuthorDetailsDto findAuthorById(@PathVariable("id") Long id){
        return authorService.findAuthorById(id);
    }

    @PutMapping
    public ResponseEntity<AuthorDto> updateAuthor(@Valid @RequestBody UpdateAuthorDto updateAuthorDto
                                                        ){
      AuthorDto updateAuthor = authorService.updateAuthor(updateAuthorDto);
        return new ResponseEntity<>(updateAuthor,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") Long id){
        authorService.deleteAuthorById(id);
        return new ResponseEntity<>("Author deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/upload/excel")
    public ResponseEntity<String> uploadAuthorsData(@RequestParam("file") MultipartFile file){
        authorService.saveAuthorToDatabase(file);
        return new ResponseEntity<>("Author Upload with file successfully", HttpStatus.CREATED);
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> uploadAuthors(@RequestParam("file") @NotNull MultipartFile file) {
        Set<CreateAuthorDto> authorsList = csvParser.parse(file);
        authorService.saveAuthorList(authorsList);
        return new ResponseEntity<>("Authors uploaded successfully", HttpStatus.OK);
    }
}
