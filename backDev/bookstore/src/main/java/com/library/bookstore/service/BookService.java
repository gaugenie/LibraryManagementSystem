package com.library.bookstore.service;


import com.library.bookstore.dto.BookDto;
import com.library.bookstore.entity.Author;
import com.library.bookstore.entity.Book;
import com.library.bookstore.execptions.AuthorNotFoundException;
import com.library.bookstore.execptions.BookApiException;
import com.library.bookstore.execptions.RessourceNotFoundException;
import com.library.bookstore.mapper.BookMapper;
import com.library.bookstore.repository.AuthorRepository;
import com.library.bookstore.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ModelMapper modelMapper;

    // create book

    public BookDto createBook(Long authorId, BookDto bookDto ){

        // map bookDto into Book entity
        Book book = modelMapper.map(bookDto, Book.class);

        // retrieve Author entity by id
        Author author = authorRepository.findById(authorId).orElseThrow(() ->
                new AuthorNotFoundException(authorId));

        // after find authorId, set into Book
        book.setAuthor(author);

        //save entity Book in Bd
        Book newBook = bookRepository.save(book);

        // return as bookDto with Mapper
        return modelMapper.map(newBook, BookDto.class);
    }

    public List<BookDto> getBooksByAuthorId(Long authorId){

        // retrieve Book by authorId
        List<Book> books= bookRepository.findByAuthorId(authorId);
        return books.stream().map(BookMapper::mapToBookDto).collect(Collectors.toList());
    }

    public BookDto getBookById(Long authorId, Long bookId){

        // retrieve author entity by id
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new RessourceNotFoundException("Could not find Author with id : "+authorId));

        // retrieve book entity by id
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new RessourceNotFoundException("Could not find Book with id : "+bookId));

        // check if comment post id is different to post id thrown exception
        if (!book.getAuthor().getId().equals(author.getId())) {
            throw new BookApiException(HttpStatus.BAD_REQUEST, "Book does not belong to author");
        }
        return modelMapper.map(book, BookDto.class);
    }

    public BookDto updateBook(Long authorId, Long bookId, BookDto bookRequest){

        // retrieve author entity by id
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new RessourceNotFoundException("Could not find Author with id : "+authorId));

        // retrieve book entity by id
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new RessourceNotFoundException("Could not find Book with id : "+bookId));

        // check if comment post id is different to post id thrown exception
        if (!book.getAuthor().getId().equals(author.getId())) {
            throw new BookApiException(HttpStatus.BAD_REQUEST, "Book does not belong to author");
        }

        book.setBookName(bookRequest.getBookName());
        book.setDescription(bookRequest.getDescription());
        book.setEditor(bookRequest.getEditor());
        book.setType(bookRequest.getType());
        book.setIsbn(bookRequest.getIsbn());
        book.setDatePublication(bookRequest.getDatePublication());
        book.setPrice(bookRequest.getPrice());

        Book updatedBook = bookRepository.save(book);

        return modelMapper.map(updatedBook, BookDto.class);
    }

    public void deleteBook(Long authorId, Long bookId){

        // retrieve author entity by id
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new RessourceNotFoundException("Could not find Author with id : "+authorId));

        // retrieve book entity by id
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new RessourceNotFoundException("Could not find Book with id : "+bookId));

        // check if comment post id is different to post id thrown exception
        if (!book.getAuthor().getId().equals(author.getId())) {
            throw new BookApiException(HttpStatus.BAD_REQUEST, "Book does not belong to author");
        }
        bookRepository.delete(book);
    }
}
