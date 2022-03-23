package org.matusikl.controlleradvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.matusikl.errorresponse.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;
    private final Logger logger = LogManager.getLogger(CustomAccessDeniedHandler.class);

    @Autowired
    public CustomAccessDeniedHandler(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        logger.debug("In CustomAccessDeniedHandler commence() method with user: {}", request.getParameter("username"));
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Sorry! Your don't have required role to access this resource!");
        errorResponse.setStatus(FORBIDDEN.value());
        errorResponse.setDateTime(ZonedDateTime.now());
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
