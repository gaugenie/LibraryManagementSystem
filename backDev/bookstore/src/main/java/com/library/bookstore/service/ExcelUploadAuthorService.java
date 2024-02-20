package com.library.bookstore.service;

import com.library.bookstore.dto.author.AuthorDto;
import com.library.bookstore.entity.Author;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVParser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ExcelUploadAuthorService {

    public boolean isValidExcelFile(MultipartFile file){

        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    @SneakyThrows
    public List<Author> getAuthorsDataFromExcel(InputStream inputStream){

        // todo change Author to AuthorDto , change InputStream par file

        List<Author> authors = new ArrayList<>();

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("authors");

            int rowIndex = 0;

            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Author author = new Author();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> author.setFirstName(cell.getStringCellValue());
                        case 1 -> author.setLastName(cell.getStringCellValue());
                        case 2 -> author.setBirthDate(cell.getLocalDateTimeCellValue().toLocalDate());
                        case 3 -> author.setBiography(cell.getStringCellValue());
                        case 4 -> author.setEmail(cell.getStringCellValue());
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                authors.add(author);
            }
        return authors;
    }
}

// todo , customise a file lombok file for slf4j
// todo enregrister le fichier meme si tous les données ne sont correct et renvoyé les données non correct dans le retour du service