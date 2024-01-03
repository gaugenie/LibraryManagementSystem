package com.library.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;
    private OffsetDateTime errorTime;
}