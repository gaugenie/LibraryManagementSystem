package com.library.bookstore.controller;

import com.library.bookstore.entity.Book;
import com.library.bookstore.execptions.RessourceNotFoundException;
import com.library.bookstore.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/books")
public class BookRestController {
    private BookService bookService;

    public BookRestController(BookService theBookService) {
        this.bookService = theBookService;
    }
    @GetMapping
    public List<Book> getAllBook(Pageable page){
        return bookService.getAllBook(page).toList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id){
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book theBook){
        return new ResponseEntity<Book>(bookService.createBook(theBook), HttpStatus.CREATED);
    }
    @PutMapping("updateBook/{id}")
    public ResponseEntity<?> updateBook(@RequestBody Book theBook, @PathVariable("id") Long id){
        try {
            bookService.updateBook(id, theBook);
            return new ResponseEntity<> ("Update book with id " +id, HttpStatus.OK);
        } catch (RessourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("deleteAuthor/{id}")
    public ResponseEntity<?>deleteBookById(@PathVariable("id") Long id){
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>("Successfully bokk deleted with id " +id ,HttpStatus.OK);
        } catch (RessourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
