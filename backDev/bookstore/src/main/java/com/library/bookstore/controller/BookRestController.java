package com.library.bookstore.controller;

import com.library.bookstore.constants.BookConstants;
import com.library.bookstore.dto.BookDto;
import com.library.bookstore.dto.ResponseDto;
import com.library.bookstore.entity.Book;
import com.library.bookstore.service.BookService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookRestController {
    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createSimpleBook(@RequestBody BookDto theBookDto){
        bookService.creatBook(theBookDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(BookConstants.STATUS_201, BookConstants.MESSAGE_201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book books = bookService.getBookById(id);
        return new ResponseEntity<>( books, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.getAllBook();
        return new ResponseEntity<>( books , HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteBook(@PathVariable Long id){
        Book book = bookService.deleteBook(id);
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(BookConstants.STATUS_200, BookConstants.MESSAGE_200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> editBook(@PathVariable Long id,
                                                @RequestBody BookDto theBookTdo){
       BookDto editBook =  bookService.editBook(id, theBookTdo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(BookConstants.STATUS_201, BookConstants.MESSAGE_201));
    }

}
