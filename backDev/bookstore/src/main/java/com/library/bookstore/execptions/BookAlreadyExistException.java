package com.library.bookstore.execptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class BookAlreadyExistException extends  RuntimeException{
    public BookAlreadyExistException(Long bookId, Long authorId) {
        super(MessageFormat.format("Book : {0} is already assigned to a Author : {1}", bookId, authorId));
    }
}
