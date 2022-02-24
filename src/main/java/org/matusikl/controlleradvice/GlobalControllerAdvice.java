package org.matusikl.controlleradvice;

import org.matusikl.errorresponse.ErrorResponse;
import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.exception.DataRelationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException exception){
        logger.debug("In GlobalControllerAdvice handleDataNotFoundException()", exception);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDateTime(ZonedDateTime.now());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        logger.info("In GlobalControllerAdvice handleDataNotFoundException() return error: {}", errorResponse);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(DataDuplicateException.class)
    public ResponseEntity<Object> handleDataDuplicateException(DataDuplicateException exception){
        logger.debug("In GlobalControllerAdvice handleDataDuplicateException()", exception);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDateTime(ZonedDateTime.now());
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        logger.info("In GlobalControllerAdvice handleDataDuplicateException() return error: {}", errorResponse);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

    @ExceptionHandler(DataRelationException.class)
    public ResponseEntity<Object> handleDataRelationException(DataRelationException exception){
        logger.debug("In GlobalControllerAdvice handleDataRelationException()", exception);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDateTime(ZonedDateTime.now());
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        logger.info("In GlobalControllerAdvice handleDataRelationException() return error: {}", errorResponse);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        logger.debug("In GlobalControllerAdvice handleMethodArgumentNotValid", exception);
        List<ErrorResponse> errorList = new ArrayList<>();
        for(ObjectError error : exception.getBindingResult().getAllErrors()){
            errorList.add(new ErrorResponse(
                    error.getDefaultMessage(),
                    HttpStatus.BAD_REQUEST.value(),
                    ZonedDateTime.now()));
        }
        logger.info("In GlobalControllerAdvice handleMethodArgumentNotValid() return error: {}", errorList);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorList);
    }
}



