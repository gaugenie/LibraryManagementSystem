package com.library.bookstore.execptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class AuthorAlreadyExistException extends  RuntimeException{
    public AuthorAlreadyExistException(String message) {
        super(message);
    }
}
