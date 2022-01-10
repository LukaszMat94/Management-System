package org.matusikl.controller;

import org.matusikl.model.Account;
import org.matusikl.service.AccountService;
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

    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService =  accountService;
    }

    @GetMapping(path = "/accounts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccount(@PathVariable ("id") Integer id){
        Account account = accountService.getAccount(id);
        return ResponseEntity
                .ok()
                .body(account);
    }

    @PostMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> addAcount(@Valid @RequestBody Account account){
        Account addedAccount = accountService.addAccount(account);
        return ResponseEntity
                .ok()
                .body(addedAccount);
    }

    @DeleteMapping(path = "/accounts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAccount(@PathVariable ("id") Integer id){
        accountService.deleteAccount(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(String.format("Account with id: %d deleted", id));
    }

    @PutMapping(path = "/accounts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> updateAccount(@PathVariable ("id") Integer id,
                                                 @Valid @RequestBody Account account) throws Exception {
        Account updatedAccount = accountService.updateAccount(account, id);
        return ResponseEntity
                .ok()
                .body(updatedAccount);
    }
}
