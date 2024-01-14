package com.library.bookstore.controller;

import com.library.bookstore.dto.BookDto;
import com.library.bookstore.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;
    private static final  Logger LOG = LoggerFactory.getLogger(BookRestController.class);

    @PostMapping("/authors/{authorId}/books")
    public ResponseEntity<BookDto> createBook(@PathVariable("authorId") Long authorId,
                                                  @Valid @RequestBody BookDto bookDto){

        BookDto book =  bookService.createBook(authorId, bookDto);
        return new ResponseEntity<>(book,HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        LOG.info("Starting get all Books method with info log level");
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/authors/{authorId}/books")
    public List<BookDto> getBooksByAuthorId(@PathVariable("authorId") Long authorId){
        return bookService.getBooksByAuthorId(authorId);
    }

    @GetMapping("/authors/{authorId}/books/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("authorId") Long authorId,
                                               @PathVariable("id") Long bookId){
            BookDto bookDto = bookService.getBookById(authorId,bookId );
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @PutMapping("/authors/{authorId}/books/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("authorId") Long authorId,
                                              @PathVariable("id") Long bookId,
                                              @Valid @RequestBody BookDto bookDto){
        BookDto updatedComment = bookService.updateBook(authorId,bookId, bookDto );

        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/authors/{authorId}/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("authorId") Long authorId,
                                             @PathVariable("id") Long bookId){
      bookService.deleteBook(authorId,bookId );
      return new ResponseEntity<>("book deleted successfully", HttpStatus.OK);
    }
}
