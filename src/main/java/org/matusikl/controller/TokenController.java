package org.matusikl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.matusikl.model.UserCredential;
import org.matusikl.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN;

@RestController
public class TokenController {

    private final TokenService tokenService;
    private final Logger logger = LogManager.getLogger(TokenController.class);

    @Autowired
    public TokenController(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @Operation(summary = "Get Tokens", description = "Get tokens for users who successfully logged in", tags = "Token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Error: not found in database"),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes")
    })
    @PostMapping(value = "/tokens", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTokens(@Valid @RequestBody UserCredential credential, HttpServletResponse response){
        logger.debug("In TokenController getTokens() user: {}", credential.getUsername());
        tokenService.getTokens(credential, response);
        logger.info("Successfully get tokens user: {}", credential.getUsername());
        return ResponseEntity.ok()
                .contentType(TEXT_PLAIN)
                .body("Logged successfully, obtain your tokens!");
    }

    @Operation(summary = "Refresh Token", description = "Get new access token for users who have valid refresh token", security = @SecurityRequirement(name = "refreshAuth"), tags = "Token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "Invalid token code"),
            @ApiResponse(responseCode = "403", description = "Authorization null"),
            @ApiResponse(responseCode = "401", description = "Invalid schema token")
    })
    @PostMapping(value = "/tokens/refresh", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        tokenService.refreshToken(request, response);
        return ResponseEntity.ok()
                .body("Refreshed successfully, get your refreshed access token");
    }
}
