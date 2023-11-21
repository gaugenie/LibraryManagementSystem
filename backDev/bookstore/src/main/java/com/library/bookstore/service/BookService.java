package com.library.bookstore.service;



import com.library.bookstore.entity.Book;
import com.library.bookstore.execptions.RessourceNotFoundException;
import com.library.bookstore.repository.AuthorRepository;
import com.library.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // retrieve all books
    public Page<Book> getAllBook(Pageable page){
        return bookRepository.findAll(page);
    }

    // retrieve book by id
    public Book getBookById(Long id) {
        Optional<Book> theBook = Optional.ofNullable(bookRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Book with id " + id + " is not find ")));
        return theBook.get();
    }
    public Book createBook(Book TheBook){
        return bookRepository.save(TheBook);
    }

    public void updateBook(Long id, Book TheBook) throws RessourceNotFoundException {
        Optional<Book> bookWithId = bookRepository.findById(id);
        if(bookWithId.isPresent()){
            Book bookToSave = bookWithId.get();
            bookToSave.setBookTitle(TheBook.getBookTitle() !=null ? TheBook.getBookTitle() : bookToSave.getBookTitle());
            bookToSave.setEditor(TheBook.getEditor() !=null ? TheBook.getEditor() : bookToSave.getEditor());
            bookToSave.setDescritpion(TheBook.getDescritpion() !=null ? TheBook.getDescritpion() : bookToSave.getDescritpion());
            bookToSave.setIsbn(TheBook.getIsbn() !=null ? TheBook.getIsbn() : bookToSave.getIsbn());
            bookToSave.setPrice(TheBook.getPrice() >= 0 ? TheBook.getPrice() : bookToSave.getPrice());
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
