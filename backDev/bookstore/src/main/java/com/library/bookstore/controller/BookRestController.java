package com.library.bookstore.controller;

import com.library.bookstore.constants.BookConstants;
import com.library.bookstore.dto.BookDto;
import com.library.bookstore.dto.ResponseDto;
import com.library.bookstore.service.BookService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class BookRestController {
    @Autowired
    private BookService bookService;

    @PostMapping("/authors/{authorId}/books")
    public ResponseEntity<ResponseDto> createBook(@PathVariable("authorId") Long authorId,
                                                  @RequestBody BookDto bookDto){
        BookDto book =  bookService.createBook(authorId, bookDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(BookConstants.STATUS_201, BookConstants.MESSAGE_201));
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
                                              @RequestBody BookDto bookDto){
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
