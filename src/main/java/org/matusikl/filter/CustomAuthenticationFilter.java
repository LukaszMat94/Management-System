package org.matusikl.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.matusikl.token.JWTTokenBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;
    private final Logger logger = LogManager.getLogger(CustomAuthenticationFilter.class);

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager,
                                      ObjectMapper objectMapper){
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        logger.debug("In CustomAuthenticationFilter attemptAuthentication() method username: {}", request.getParameter("username"));
        String username;
        String password;
        try {
            Map<String, String> credentials = objectMapper.readValue(request.getInputStream(), Map.class);
            username = credentials.get("username");
            password = credentials.get("password");
        } catch (IOException exception) {
            logger.error("IOException at CustomAuthenticationFilter attemptAuthentication() method username: {}", request.getParameter("username"));
            throw new RuntimeException(exception);
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        logger.info("Successfully created AuthenticationToken in attemptAuthentication() method with user: {}", username);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        logger.debug("In CustomAuthenticationFilter successfulAuthentication() method username: {}", request.getParameter("username"));
        User user = (User) authResult.getPrincipal();
        List<?> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String accessToken = JWTTokenBuilder.getAccessToken(user.getUsername(), request.getRequestURL().toString(), "roles", roles);
        String refreshToken = JWTTokenBuilder.getRefreshToken(user.getUsername(), request.getRequestURL().toString());

        try {
            Map<String, String> tokens = new HashMap<>();
            tokens.put("access_token", accessToken);
            tokens.put("refresh_token", refreshToken);
            response.setContentType(APPLICATION_JSON_VALUE);
            objectMapper.writeValue(response.getOutputStream(), tokens);
            logger.info("Successfully sended tokens to user: {} in attemptAuthentication() method", request.getParameter("username"));
        }
        catch(IOException exception){
            logger.error("IOException at CustomAuthenticationFilter successfulAuthentication() method username: {}", request.getParameter("username"));
            throw new RuntimeException(exception);
        }
    }
}
