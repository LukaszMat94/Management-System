package org.matusikl.controlleradvice;

import com.alibaba.fastjson.JSON;
import org.matusikl.errorresponse.ErrorResponse;
import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.Map;

@EnableWebMvc
@RestControllerAdvice
@ComponentScan(basePackages = {"org.matusikl.errorresponse", "org.matusikl.controlleradvice", "org.matusikl.config"})
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public Map<String, Object> handleDataNotFoundException(DataNotFoundException exception){

            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(exception.getMessage());
            errorResponse.setTime(LocalDateTime.now());
            errorResponse.setStatus(HttpStatus.NOT_FOUND.value());

        return getJsonMap(errorResponse);
    }

    @ExceptionHandler(DataDuplicateException.class)
    public Map<String, Object> handleDataDuplicateException(DataDuplicateException exception){

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTime(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.CONFLICT.value());

        return getJsonMap(errorResponse);
    }

    public Map<String, Object> getJsonMap(ErrorResponse errorResponse){
        return JSON.parseObject(JSON.toJSONString(errorResponse), Map.class);
    }

}
