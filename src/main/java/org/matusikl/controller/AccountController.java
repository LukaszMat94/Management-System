package org.matusikl.controller;

import org.matusikl.model.Account;
import org.matusikl.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class AccountController {

    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService =  accountService;
    }

    @GetMapping(value = "/get/account/{id}", produces = "application/json")
    public ResponseEntity<Account> getAccount(@PathVariable ("id") Integer id){
        Account account = accountService.getAccount(id);
        return ResponseEntity
                .ok()
                .body(account);
    }

    @PostMapping(value = "/add/account", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addAcount(@Valid @RequestBody Account account){
        accountService.addAccount(account);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Account added");
    }

    @DeleteMapping(value = "/delete/account/{id}", produces = "application/json")
    public ResponseEntity<String> deleteAccount(@PathVariable ("id") Integer id){
        accountService.deleteAccount(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Account deleted");
    }

    @PutMapping(value = "/update/account/{id}", produces = "application/json")
    public ResponseEntity<Account> updateAccount(@PathVariable ("id") Integer id,
                                                 @Valid @RequestBody Account account){
        Account updatedAccount = accountService.updateAccount(account, id);
        return ResponseEntity
                .ok()
                .body(updatedAccount);
    }
}
