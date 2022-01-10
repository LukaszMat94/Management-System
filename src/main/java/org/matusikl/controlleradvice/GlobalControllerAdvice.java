package org.matusikl.controlleradvice;

import org.matusikl.errorresponse.ErrorResponse;
import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException exception){

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDateTime(ZonedDateTime.now());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler(DataDuplicateException.class)
    public ResponseEntity<Object> handleDataDuplicateException(DataDuplicateException exception){

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDateTime(ZonedDateTime.now());
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<ErrorResponse> errorList = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()){
            errorList.add(new ErrorResponse(
                    error.getDefaultMessage(),
                    HttpStatus.BAD_REQUEST.value(),
                    ZonedDateTime.now()));
        }
        return ResponseEntity
                .badRequest()
                .body(errorList);
    }
}



