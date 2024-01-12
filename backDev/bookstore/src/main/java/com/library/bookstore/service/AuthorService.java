package com.library.bookstore.service;

import com.library.bookstore.dto.AuthorDto;
import com.library.bookstore.execptions.*;
import com.library.bookstore.mapper.AuthorAutoMapper;
import com.library.bookstore.repository.AuthorRepository;
import com.library.bookstore.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final ExcelUploadAuthorService excelUploadAuthorService;

    // create author
    @Transactional
    public AuthorDto createAuthor(AuthorDto authorDto){

        Author author = AuthorAutoMapper.MAPPER.mapToAuthor(authorDto);
        Author newAuthor = authorRepository.save(author);
        // convert entity to postDto
        return AuthorAutoMapper.MAPPER.mapToAuthorDto(newAuthor);
    }

    // get all author
    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthor(){

        List<Author> authors;
        try {
            authors = authorRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return authors.stream().map(author -> AuthorAutoMapper.MAPPER.mapToAuthorDto(author)).toList();
    }

    @Transactional(readOnly = true)
    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new RessourceNotFoundException("Could not find Author with id : "+id));
        return AuthorAutoMapper.MAPPER.mapToAuthorDto(author);
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

        return AuthorAutoMapper.MAPPER.mapToAuthorDto(updateAuthor);
    }
    @Transactional
    public void deleteAuthorById(Long id){
        Author author = authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException(id));
        authorRepository.delete(author);

    }
    public void saveAuthorToDatabase(MultipartFile file){

        if (excelUploadAuthorService.isValidExcelFile(file)) {

            try {
                List<Author> authors = excelUploadAuthorService.getAuthorsDataFromExcel(file.getInputStream());
                authorRepository.saveAll(authors);
            } catch (Exception e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }
}

