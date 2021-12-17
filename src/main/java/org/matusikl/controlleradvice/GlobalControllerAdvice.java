package org.matusikl.controlleradvice;

import org.matusikl.errorresponse.ErrorResponse;
import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException exception){

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDateTime(ZonedDateTime.now());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(DataDuplicateException.class)
    public ResponseEntity<Object> handleDataDuplicateException(DataDuplicateException exception){

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDateTime(ZonedDateTime.now());
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        return ResponseEntity.badRequest().body(errorResponse);
    }

}



