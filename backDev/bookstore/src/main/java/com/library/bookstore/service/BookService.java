package com.library.bookstore.service;

import com.library.bookstore.entity.Book;
import com.library.bookstore.execptions.RessourceNotFoundException;
import com.library.bookstore.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // retrieve all books
    public List<Book> getAllBooks(){
        List<Book> books = StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return books;
    }

    // retrieve book by id
    public Book getBookById(Long bookId) {
        Book book = getBook(bookId);
        return book;
    }
    public Book getBook(Long bookId) {
        Optional<Book> theBook = Optional.ofNullable(bookRepository.findById(bookId)
                .orElseThrow(() -> new RessourceNotFoundException("Book with id " + bookId + " is not find ")));
        return theBook.get();
    }

    @Transactional
    public Book createBook(Book theBook){
        Book book = new Book();
        book.setBookName(theBook.getBookName());
        book.setIsbn(theBook.getIsbn());
        book.setPrice(theBook.getPrice());
        book.setDescritpion(theBook.getDescritpion());
        book.setCategory(theBook.getCategory());
        book.setDatePublication(theBook.getDatePublication());
        book.setPrice(theBook.getPrice());
        if (theBook.getAuthor() == null) {
            throw new RessourceNotFoundException("Author for this book is not found");
        }
        Book bookToSave = bookRepository.save(book);
        return bookToSave;
    }
    @Transactional
    public void updateBook(Long id, Book TheBook) throws RessourceNotFoundException {
        Optional<Book> bookWithId = bookRepository.findById(id);
        if(bookWithId.isPresent()){
            Book bookToSave = bookWithId.get();
            bookToSave.setBookName(TheBook.getBookName() !=null ? TheBook.getBookName() : bookToSave.getBookName());
            bookToSave.setCategory(TheBook.getCategory() !=null ? TheBook.getCategory() : bookToSave.getCategory());
            bookToSave.setDescritpion(TheBook.getDescritpion() !=null ? TheBook.getDescritpion() : bookToSave.getDescritpion());
            bookToSave.setIsbn(TheBook.getIsbn() !=null ? TheBook.getIsbn() : bookToSave.getIsbn());
            bookToSave.setDatePublication(TheBook.getDatePublication() !=null ? TheBook.getDatePublication() : bookToSave.getDatePublication());
            bookToSave.setDatePublication(TheBook.getDatePublication() !=null ? TheBook.getDatePublication() : bookToSave.getDatePublication());
            bookRepository.save(bookToSave);
        }
    }

    public void deleteBook(Long id) throws RessourceNotFoundException{
        Optional<Book> bookWithId = bookRepository.findById(id);
        if(!bookWithId.isPresent()){
            throw new RessourceNotFoundException("the Book with the id " +id+ " is not found");
        }else {
            bookRepository.deleteById(id);
        }
    }
}
