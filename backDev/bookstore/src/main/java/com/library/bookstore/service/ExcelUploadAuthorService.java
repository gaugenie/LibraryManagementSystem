package com.library.bookstore.service;

import com.library.bookstore.entity.Author;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class ExcelUploadAuthorService {

    public boolean isValidExcelFile(MultipartFile file){

        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public List<Author> getAuthorsDataFromExcel(InputStream inputStream){

        List<Author> authors = new ArrayList<>();

        try {
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

        } catch (IOException e) {
            e.getStackTrace();
        }
        return authors;
    }
}
