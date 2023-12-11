package com.library.bookstore.controller;

import com.library.bookstore.entity.Book;
import com.library.bookstore.execptions.RessourceNotFoundException;
import com.library.bookstore.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path="/api/v1/books" , produces = {MediaType.APPLICATION_JSON_VALUE})
public class BookRestController {
    private BookService bookService;

  

}
