package com.library.bookstore.mapper;

import com.library.bookstore.dto.BookDto;
import com.library.bookstore.entity.Book;

public class BookMapper {
    public static BookDto mapToBookCreateDto(Book book, BookDto bookDto) {
        bookDto.setBookTitle(book.getBookTitle());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setPrice(book.getPrice());
        bookDto.setEditor(book.getEditor());
        bookDto.setType(book.getType());
        bookDto.setBookDescription(book.getDescritpion());
        bookDto.setAuthorId(book.getAuthor().getId());
        return bookDto;
    }

    public static Book mapToBook(BookDto bookDto, Book book) {
        book.setBookTitle(bookDto.getBookTitle());
        book.setIsbn(bookDto.getIsbn());
        book.setPrice(bookDto.getPrice());
        book.setEditor(bookDto.getEditor());
        book.setType(bookDto.getType());
        book.setDescritpion(bookDto.getBookDescription());
        return book;
    }
}
