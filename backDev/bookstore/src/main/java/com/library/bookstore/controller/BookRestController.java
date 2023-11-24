package com.library.bookstore.controller;

import com.library.bookstore.entity.Book;
import com.library.bookstore.execptions.RessourceNotFoundException;
import com.library.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/book")
public class BookRestController {
    private BookService bookService;

    public BookRestController(BookService theBookService) {
        this.bookService = theBookService;
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        return new ResponseEntity<> (book, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> book = bookService.getAllBooks();
        return new ResponseEntity<> (book, HttpStatus.OK);
    }
    @PostMapping("/createBook")
    public ResponseEntity<Book> createBook(@RequestBody Book theBook){
        Book book = bookService.createBook(theBook);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("/editBook/{id}")
    public ResponseEntity<?> editBook(@RequestBody Book theBook, @PathVariable("id") Long id){
        try {
            bookService.updateBook(id, theBook);
            return new ResponseEntity<> ("Update book with id " +id, HttpStatus.OK);
        } catch (RessourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteBookById(@PathVariable("id") Long id){
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>("Successfully bokk deleted with id " +id ,HttpStatus.OK);
        } catch (RessourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
