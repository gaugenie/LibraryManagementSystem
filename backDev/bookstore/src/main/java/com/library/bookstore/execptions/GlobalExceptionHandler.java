package com.library.bookstore.execptions;

import com.library.bookstore.entity.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RessourceNotFoundException.class)
    public ResponseEntity<ErrorObject> HandleUserException(RessourceNotFoundException ex, WebRequest request){
        ErrorObject eObject = new ErrorObject();

        eObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        eObject.setMessage(ex.getMessage());
        eObject.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(eObject, HttpStatus.NOT_FOUND);
    }
}
