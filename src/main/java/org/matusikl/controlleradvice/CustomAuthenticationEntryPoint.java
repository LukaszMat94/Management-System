package org.matusikl.controlleradvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.matusikl.errorresponse.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;
    private final Logger logger = LogManager.getLogger(CustomAuthenticationEntryPoint.class);

    @Autowired
    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        logger.debug("In CustomAuthenticationEntryPoint commence() method with user: {}", request.getParameter("username"));
        System.out.println("Authentication Entry Point method");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Sorry! You need to login first in order to perform this action!");
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setDateTime(ZonedDateTime.now());
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
