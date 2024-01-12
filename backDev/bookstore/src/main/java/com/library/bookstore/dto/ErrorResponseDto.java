package com.library.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.OffsetDateTime;


@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDto {

    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;
    private OffsetDateTime errorTime;
}

