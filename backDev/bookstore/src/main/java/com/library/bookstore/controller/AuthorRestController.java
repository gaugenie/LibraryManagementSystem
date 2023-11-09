package com.library.bookstore.controller;

import com.library.bookstore.entity.Author;
import com.library.bookstore.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorRestController {
    private AuthorService authorsService;

    public AuthorRestController(AuthorService TheAuthorsService) {
        this.authorsService = TheAuthorsService;
    }
    @GetMapping("/authors")
    public List<Author> findAll(){
        return authorsService.findAll();
    }

    @PostMapping("/authors")
    public Author addAuthors(@RequestBody Author theAuthors){

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        theAuthors.setId(0);
        return authorsService.save(theAuthors);
    }

    @PutMapping("/authors")
    public Author updateEmployee(@RequestBody Author theAuthors){

        return authorsService.save(theAuthors);
    }

    @DeleteMapping("/employees/{authorsId}")
    public String deleteEmployee(@PathVariable int authorsId){

        Author tempEmployee = authorsService.findById(authorsId);

        // throw exception if null

        if(tempEmployee == null){
            throw new RuntimeException("Author id not found - " + authorsId);
        }
        authorsService.deleteById(authorsId);

        return "Deleted authors id - " + authorsId;
    }
}
