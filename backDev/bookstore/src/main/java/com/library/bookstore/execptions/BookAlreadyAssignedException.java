package com.library.bookstore.execptions;

import java.text.MessageFormat;

public class BookAlreadyAssignedException extends  RuntimeException{
    public BookAlreadyAssignedException(final Long bookId, final Long authorId) {
        super(MessageFormat.format("Book: {0} is already assigned to author: {}", bookId, authorId));
    }
}
