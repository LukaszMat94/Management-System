package org.matusikl.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.matusikl.errorresponse.ErrorResponse;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.model.Account;
import org.matusikl.model.UserCredential;
import org.matusikl.repository.AccountRepository;
import org.matusikl.token.JWTTokenBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
public class TokenService {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final Logger logger = LogManager.getLogger(TokenService.class);

    @Autowired
    public TokenService(AccountService accountService,
                        AccountRepository accountRepository,
                        AuthenticationManager authenticationManager,
                        PasswordEncoder passwordEncoder,
                        ObjectMapper objectMapper){
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = objectMapper;
    }

    public void getTokens(UserCredential credential, HttpServletResponse response){
        logger.debug("In TokenService getTokens method user: {}", credential.getUsername());
        authenticate(credential);
        String username = credential.getUsername();
        UserDetails userDetails = accountService.loadUserByUsername(username);
        String accessToken = JWTTokenBuilder.getAccessToken(userDetails.getUsername(), "roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        String refreshToken = JWTTokenBuilder.getRefreshToken(userDetails.getUsername());
        response.addHeader("access_token", accessToken);
        response.addHeader("refresh_token", refreshToken);
        logger.info("Returned tokens for user: {}", username);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.debug("In TokenService refreshToken method");
        String refreshToken = request.getHeader(AUTHORIZATION);
        try{
            if(refreshToken.startsWith("Bearer ")){
                String token = refreshToken.substring(("Bearer ").length());
                String username = JWTTokenBuilder.getDecodedRefreshToken(token);
                logger.info("Decoded token from user: {}", username);
                UserDetails userDetails = accountService.loadUserByUsername(username);
                String newAccessToken = JWTTokenBuilder.getAccessToken(userDetails.getUsername(),
                        "roles",
                        userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
                response.addHeader("access_token", newAccessToken);
                response.addHeader("refresh_token", token);
                logger.info("Updated access token for user: {}", username);
            }
            else{
                logger.error("Error occured in TokenService refreshToken() - Invalid schema token");
                ErrorResponse errorResponse = new ErrorResponse(
                        "Incorrect schema for token!",
                        UNAUTHORIZED.value(),
                        ZonedDateTime.now());
                objectMapper.writeValue(response.getOutputStream(), errorResponse);
            }
        }
        catch(NullPointerException exception){
            logger.error("NullPointerException in TokenService refreshToken() - Authorization is null");
            ErrorResponse errorResponse = new ErrorResponse(
                    "Authorization is null",
                    FORBIDDEN.value(),
                    ZonedDateTime.now());
            objectMapper.writeValue(response.getOutputStream(), errorResponse);
        }
        catch(Exception exception){
            logger.error("Exception occured in TokenService refreshToken() - exception:", exception);
            ErrorResponse errorResponse = new ErrorResponse(
                    exception.getMessage(),
                    FORBIDDEN.value(),
                    ZonedDateTime.now());
            objectMapper.writeValue(response.getOutputStream(), errorResponse);
        }
    }

    private void authenticate(UserCredential credential){
        logger.debug("In TokenService authenticate method user: {}", credential.getUsername());
        String username = credential.getUsername();
        String password = credential.getPassword();
        validateCredentials(username, password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authenticationToken);
        logger.info("In TokenService authenticated credentials user: {}", username);
    }

    private void validateCredentials(String username, String password){
        logger.debug("In TokenService validateCredentials method user: {}", username);
        Account account = accountRepository.findByLogin(username).orElseThrow(() -> {
            logger.error("In TokenService validate method - validate failed user: {} Account not exist", username);
            throw new DataNotFoundException(String.format("Account not exist with login: %s", username));
        });
        if(!passwordEncoder.matches(password, account.getPassword())){
            logger.error("In TokenService validate method - validate failed user: {} Password not match", username);
            throw new DataNotFoundException(String.format("Given password not match to this account: %s", username));
        }
        logger.info("In TokenService validate method - validated successfully user: {}", username);
    }
}
