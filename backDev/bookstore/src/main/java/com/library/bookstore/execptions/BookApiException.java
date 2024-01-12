package com.library.bookstore.execptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BookApiException extends  RuntimeException{

    private final HttpStatus status;
    private final String message;

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
