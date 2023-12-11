package com.library.bookstore.service;

import com.library.bookstore.entity.Book;
import com.library.bookstore.execptions.AuthorNotFoundException;
import com.library.bookstore.execptions.BookAlreadyAssignedException;
import com.library.bookstore.execptions.RessourceNotFoundException;
import com.library.bookstore.repository.AuthorRepository;
import com.library.bookstore.entity.Author;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private final BookService bookService;
    @Autowired
    public AuthorService(AuthorRepository TheAuthorRepository, BookService theBookService) {
        this.authorRepository = TheAuthorRepository;
        this.bookService = theBookService;
    }

    // retrieve Author
    public Author getAuthor(Long authorId) {
        Optional<Author> theAuthor = Optional.ofNullable(authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId)));
        return theAuthor.get();
    }

    // retrieve All authors
    public List<Author> getAllAuthors(){
        List<Author> authors = StreamSupport
                .stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return authors;
    }

    public Author addAuthor(Author theAuthor){
        Author author = new Author();
        author.setFirstName(theAuthor.getFirstName());
        author.setLastName(theAuthor.getLastName());
        author.setBirthDate(theAuthor.getBirthDate());
        author.setBiography(theAuthor.getBiography());
        return authorRepository.save(theAuthor);
    }

    @Transactional
     public void editAuthor(Long id, Author TheAuthor) throws RessourceNotFoundException {
        Optional<Author> authorWithId = authorRepository.findById(id);
        if(authorWithId.isPresent()){
            Author authorToEdit = authorWithId.get();
            authorToEdit.setBiography(TheAuthor.getBiography() !=null ? TheAuthor.getBiography() : authorToEdit.getBiography());
            authorToEdit.setFirstName(TheAuthor.getFirstName() !=null ? TheAuthor.getFirstName() : authorToEdit.getFirstName());
            authorToEdit.setLastName(TheAuthor.getLastName() !=null ? TheAuthor.getLastName() : authorToEdit.getLastName());
            authorToEdit.setBirthDate(TheAuthor.getBirthDate() !=null ? TheAuthor.getBirthDate() : authorToEdit.getBirthDate());
            authorRepository.save(authorToEdit);
        }
     }

     public void deleteAuthor(Long id) throws RessourceNotFoundException{
         Optional<Author> authorWithId = authorRepository.findById(id);
         if(!authorWithId.isPresent()){
             throw new AuthorNotFoundException(id);
         }else {
             authorRepository.deleteById(id);
         }

     }
    @Transactional
    public  Author addBookToAuthor(Long authorId, Long bookId){
        Author author = getAuthor(authorId);
        Book book = bookService.getBook(bookId);

        if (Objects.nonNull(book.getAuthor())) {
        throw new BookAlreadyAssignedException(bookId,
                book.getAuthor().getId());
        }
        author.addBook(book);
        return author;
    }
    @Transactional
    public Author removeBookFromAuthor(Long authorId, Long bookId){
        Author author = getAuthor(authorId);
        Book book = bookService.getBook(bookId);
        author.removeBook(book);
        return author;

    }
}

