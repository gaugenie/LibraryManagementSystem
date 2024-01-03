package com.library.bookstore.execptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@Data
public class BookApiException extends  RuntimeException{

    private HttpStatus status;
    private String message;

    public BookApiException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public BookApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
