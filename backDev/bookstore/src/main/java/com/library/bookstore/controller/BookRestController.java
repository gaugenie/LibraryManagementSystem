package com.library.bookstore.controller;

import com.library.bookstore.entity.Book;
import com.library.bookstore.entity.dto.BookDto;
import com.library.bookstore.execptions.RessourceNotFoundException;
import com.library.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/book")
public class BookRestController {
    private BookService bookService;

    @Autowired
    public BookRestController(BookService theBookService) {
        this.bookService = theBookService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable final Long id){
        Book book = bookService.getBook(id);
        return new ResponseEntity<> (BookDto.from(book), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        List<BookDto> bookDtos = books.stream().map(BookDto::from).collect(Collectors.toList());
        return new ResponseEntity<> (bookDtos, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody final BookDto bookDto){
        Book book = bookService.addBook(Book.from(bookDto));
        return new ResponseEntity<>(bookDto.from(book), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editBook(@RequestBody Book theBook, @PathVariable final Long id){
        try {
            bookService.editBook(id, theBook);
            return new ResponseEntity<> ("Update book with id " +id, HttpStatus.OK);
        } catch (RessourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteBookById(@PathVariable("id") final Long id){
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>("Successfully bokk deleted with id " +id ,HttpStatus.OK);
        } catch (RessourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
