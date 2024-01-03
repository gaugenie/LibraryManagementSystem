package com.library.bookstore.service;


import com.library.bookstore.dto.AuthorDto;
import com.library.bookstore.execptions.*;
import com.library.bookstore.repository.AuthorRepository;
import com.library.bookstore.entity.Author;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final ModelMapper modelMapper;

    // create author
    @Transactional
    public AuthorDto createAuthor(AuthorDto authorDto){

        // convert PostDto to entity Post
        Author author = modelMapper.map(authorDto, Author.class);
        //
        Author newAuthor = authorRepository.save(author);
        // convert entity to postDto
        AuthorDto authorResponse = modelMapper.map(newAuthor, AuthorDto.class);

        return authorResponse;
    }

    // get all author
    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthor(){
        List<Author> authors = authorRepository.findAll();
        // TODO: 02/01/2024 change AuthorMapper to modelMapper and install Sonar
        return authors.stream().map((author) -> modelMapper.map(author, AuthorDto.class)).toList();
    }
    // todo dans change method de service

    // TODO: 02/01/2024 Change ModelMapper to Mapstruct

    @Transactional(readOnly = true)
    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new RessourceNotFoundException("Could not find Author with id : "+id));
        return modelMapper.map(author, AuthorDto.class);
    }

    @Transactional
    public AuthorDto updateAuthor(AuthorDto authorDto, Long id){

        // get post by id from database
        Author author = authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException(id));

        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setBirthDate(authorDto.getBirthDate());
        author.setEmail(authorDto.getEmail());
        author.setBiography(authorDto.getBiography());

        Author updateAuthor = authorRepository.save(author);

        return modelMapper.map(updateAuthor, AuthorDto.class);
    }
    @Transactional
    public void deleteAuthorById(Long id){
        Author author = authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException(id));
        authorRepository.delete(author);

    }

}

