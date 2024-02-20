package com.library.bookstore.utils;

import com.library.bookstore.dto.author.CreateAuthorDto;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CSVParser {
    public Set<CreateAuthorDto> parse(MultipartFile file) {
        try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            HeaderColumnNameMappingStrategy<CreateAuthorDto> strategy =
                    new HeaderColumnNameMappingStrategy<>();
            strategy.setType(CreateAuthorDto.class);
            CsvToBean<CreateAuthorDto> csvToBean =
                    new CsvToBeanBuilder<CreateAuthorDto>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
           return csvToBean.parse()
                    .stream()
                    .map(csvLine -> CreateAuthorDto.builder()
                            .firstName(csvLine.getFirstName())
                            .lastName(csvLine.getLastName())
                            .birthDate(csvLine.getBirthDate())
                            .biography(csvLine.getBiography())
                            .email(csvLine.getEmail())
                            .build()
                    )
                    .collect(Collectors.toSet()); // collect all authors (entity) to set (set<author> authors)
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
