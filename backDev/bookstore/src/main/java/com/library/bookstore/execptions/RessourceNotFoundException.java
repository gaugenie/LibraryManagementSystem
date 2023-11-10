package com.library.bookstore.execptions;

public class RessourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RessourceNotFoundException(String message) {
        super(message);
    }
}
