package com.library.bookstore.execptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)

public class AuthorAlreadyExistException extends  RuntimeException{
    public AuthorAlreadyExistException(String message) {
        super(message);
    }
}
