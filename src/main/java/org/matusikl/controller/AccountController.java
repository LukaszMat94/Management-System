package org.matusikl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.matusikl.model.Account;
import org.matusikl.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

@RestController
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService =  accountService;
    }

    @Operation(summary = "Get account by id", description = "Get simple account by specified id", tags = "Account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the Account with specified id",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Account.class))}),
        @ApiResponse(responseCode = "404", description = "Error: not found in database",
            content = @Content)})
    @GetMapping(path = "/accounts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccount(@PathVariable ("id") Integer id){
        Account account = accountService.getAccount(id);
        return ResponseEntity
            .ok()
            .body(account);
    }

    @Operation(summary = "Save account", description = "Save account with specified values", tags = "Account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account saved in database",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Account.class))}),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - data already exist in database",
            content = @Content)})
    @PostMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> addAcount(@Valid @RequestBody Account account){
        Account addedAccount = accountService.addAccount(account);
        return ResponseEntity
                .ok()
                .body(addedAccount);
    }

    @Operation(summary = "Delete account", description = "Delete account by specified id", tags = "Account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account with specified id deleted from database",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Account.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @DeleteMapping(path = "/accounts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAccount(@PathVariable ("id") Integer id){
        accountService.deleteAccount(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(String.format("Account with id: %d deleted", id));
    }

    @Operation(summary = "Update account", description = "Update simple account by specified id", tags = "Account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated account with specified id in database",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Account.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - data already exist in database",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content)})
    @PutMapping(path = "/accounts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> updateAccount(@PathVariable ("id") Integer id,
                                                 @Valid @RequestBody Account account) throws Exception {
        Account updatedAccount = accountService.updateAccount(account, id);
        return ResponseEntity
                .ok()
                .body(updatedAccount);
    }
}
