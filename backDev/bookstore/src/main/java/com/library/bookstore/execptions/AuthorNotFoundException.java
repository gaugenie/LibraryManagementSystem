package com.library.bookstore.execptions;

import java.text.MessageFormat;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long id) {
        super(MessageFormat.format("Could not find Author with id : {0}", id));
    }
}
