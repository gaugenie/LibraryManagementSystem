package com.library.bookstore.service;

import com.library.bookstore.dto.author.AuthorDto;
import com.library.bookstore.dto.author.AuthorDetailsDto;
import com.library.bookstore.dto.author.CreateAuthorDto;
import com.library.bookstore.dto.author.UpdateAuthorDto;
import com.library.bookstore.execptions.*;
import com.library.bookstore.mapper.AuthorMapper;
import com.library.bookstore.repository.AuthorRepository;
import com.library.bookstore.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ExcelUploadAuthorService excelUploadAuthorService;
    private final AuthorMapper authorMapper;

    // create author
    //@Transactional
    public void saveAuthorList(Set<CreateAuthorDto> authorDtoList) {
        for (CreateAuthorDto authorDto : authorDtoList) {
            saveAuthor(authorDto);
        }
    }

    @Transactional
    public AuthorDetailsDto saveAuthor(CreateAuthorDto createAuthorDto) {
        Author author = authorMapper.mapToAuthor(createAuthorDto);
        Author newAuthor = authorRepository.save(author);
        return authorMapper.mapToAuthorDetailsDto(newAuthor); // convert entity to AuthorDto
    }

    // get all author
    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(authorMapper::mapToAuthorDto).toList();
    }

    @Transactional(readOnly = true)
    public AuthorDetailsDto findAuthorById(Long id) {
        return authorRepository.findById(id).map(authorMapper::mapToAuthorDetailsDto).orElseThrow(
                () -> new RessourceNotFoundException("Could not find Author with id : " + id));
    }

    @Transactional
    public AuthorDto updateAuthor(UpdateAuthorDto updateAuthorDto) {

        // get post by id from database
        Author author = authorRepository.findById(updateAuthorDto.getId()).orElseThrow(() ->
                new AuthorNotFoundException(updateAuthorDto.getId()));

        authorMapper.updateAuthor(updateAuthorDto, author); // map updateAuthorDto to author (entity)
        Author updatedAuthor = authorRepository.save(author);

        return authorMapper.mapToAuthorDto(updatedAuthor);
    }

    @Transactional
    public void deleteAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException(id));
        authorRepository.delete(author);

    }

    public void saveAuthorToDatabase(MultipartFile file) {

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

