package com.library.bookstore.service;


import com.library.bookstore.dto.BookDto;
import com.library.bookstore.entity.Book;
import com.library.bookstore.execptions.BookNotFoundException;
import com.library.bookstore.execptions.RessourceNotFoundException;
import com.library.bookstore.repository.AuthorRepository;
import com.library.bookstore.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@AllArgsConstructor
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() ->
                new BookNotFoundException(bookId));
    }

    @Transactional
    public void creatBook(BookDto theBookDto){
        Book book = new Book();
        book.setBookTitle(theBookDto.getBookTitle());
        book.setIsbn(theBookDto.getIsbn());
        book.setPrice(theBookDto.getPrice());
        book.setPrice(theBookDto.getPrice());
        book.setDescritpion(theBookDto.getBookDescription());
        bookRepository.save(book);
    }

    public List<Book> getAllBook(){
        return StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Book deleteBook(Long id){
        Book book = getBookById(id);
        bookRepository.deleteById(id);
        return book;
    }

    @Transactional
    public BookDto editBook(Long id, BookDto TheBookDto) throws RessourceNotFoundException {
        Optional<Book> bookWithId = bookRepository.findById(id);
        if(bookWithId.isPresent()){
            Book bookToSave = bookWithId.get();
            bookToSave.setBookTitle(TheBookDto.getBookTitle() !=null ? TheBookDto.getBookTitle() : bookToSave.getBookTitle());
            bookToSave.setDescritpion(TheBookDto.getBookDescription() !=null ? TheBookDto.getBookDescription() : bookToSave.getDescritpion());
            bookToSave.setIsbn(TheBookDto.getIsbn() !=null ? TheBookDto.getIsbn() : bookToSave.getIsbn());
            bookToSave.setEditor(TheBookDto.getEditor() !=null ? TheBookDto.getEditor() : bookToSave.getEditor());
            bookToSave.setPrice(TheBookDto.getPrice() != 0 ? TheBookDto.getPrice() : bookToSave.getPrice());
            bookRepository.save(bookToSave);
        }

        return TheBookDto;
    }
}
