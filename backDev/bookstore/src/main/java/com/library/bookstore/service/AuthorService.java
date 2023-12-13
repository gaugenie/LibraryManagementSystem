package com.library.bookstore.service;

import com.library.bookstore.constants.BookConstants;
import com.library.bookstore.dto.AuthorDto;
import com.library.bookstore.entity.Book;
import com.library.bookstore.execptions.*;
import com.library.bookstore.mapper.AuthorMapper;
import com.library.bookstore.repository.AuthorRepository;
import com.library.bookstore.entity.Author;
import com.library.bookstore.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class AuthorService {
    @Autowired
    private  AuthorRepository authorRepository;
    @Autowired
    private  BookService bookService;

    public void createAuthor(AuthorDto authorDto){
        Author author = AuthorMapper.mapToAuthor(authorDto, new Author());
        authorRepository.save(author);
    }

    public List<Author> getAllAuthor(){
        return StreamSupport
                .stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException(id));
    }

    public Author deleteAuthor(Long id){
        Author author = getAuthorById(id);
        authorRepository.deleteById(id);
        return author;
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

    @Transactional
    public Author addBookToAuthor(Long authorId, Long bookId){
        Author author = getAuthorById(authorId);
        Book book = bookService.getBookById(bookId);
        if(Objects.nonNull(book.getAuthor())){
            throw new BookAlreadyExistException(bookId, book.getAuthor().getId());
        }
        author.getBooks().add(book);
        return author;
    }

    @Transactional
    public Author removeBookFromAuthor(Long authorId, Long bookId){
        Author author = getAuthorById(authorId);
        Book book = bookService.getBookById(bookId);
        author.getBooks().remove(book);
        return author;
    }
}

