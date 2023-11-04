package com.library.bookstore.rest;

import com.library.bookstore.entity.Authors;
import com.library.bookstore.service.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorsRestController {
    private AuthorsService authorsService;

    public AuthorsRestController(AuthorsService TheAuthorsService) {
        this.authorsService = TheAuthorsService;
    }
    @GetMapping("/authors")
    public List<Authors> findAll(){
        return authorsService.findAll();
    }

    @PostMapping("/authors")
    public Authors addAuthors(@RequestBody Authors theAuthors){

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        theAuthors.setId(0);
        return authorsService.save(theAuthors);
    }

    @PutMapping("/authors")
    public Authors updateEmployee(@RequestBody Authors theAuthors){

        return authorsService.save(theAuthors);
    }

    @DeleteMapping("/employees/{authorsId}")
    public String deleteEmployee(@PathVariable int authorsId){

        Authors tempEmployee = authorsService.findById(authorsId);

        // throw exception if null

        if(tempEmployee == null){
            throw new RuntimeException("Authors id not found - " + authorsId);
        }
        authorsService.deleteById(authorsId);

        return "Deleted authors id - " + authorsId;
    }
}
