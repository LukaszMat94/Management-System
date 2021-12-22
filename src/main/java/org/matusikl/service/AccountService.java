package org.matusikl.service;

import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.model.Account;
import org.matusikl.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account getAccount(Integer id){
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Get failed! Account not exist in database with id: " + id) {
                });
        return account;
    }

    @Transactional
    public Account addAccount(Account account){
        boolean accountExist = accountRepository
                .findByLogin(account.getLogin())
                .isPresent();

        if(accountExist){
            throw new DataDuplicateException("Add failed! Account already exist with login: " + account.getLogin());
        }
        else {
            Account addedAccount = accountRepository.save(account);
            return addedAccount;
        }
    }

    @Transactional
    public void deleteAccount(Integer id){
        boolean accountExist = accountRepository.existsById(id);
        if(!accountExist){
            throw new DataNotFoundException("Delete failed! Account with id: " + id + " not exist in database");
        }
        else {
            accountRepository.deleteById(id);
        }
    }

    @Transactional
    public Account updateAccount(Account account, Integer id){
        Account accountDB = accountRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Update failed! Account with id: " + id + " not exist in database!"));

        boolean otherAccountWithSameLogin = accountRepository
                .findByLoginAndIdAccountNot(account.getLogin(), id)
                .isPresent();
        if(otherAccountWithSameLogin){
            throw new DataNotFoundException("Update failed! Account with login: " + account.getLogin() + " already exist in database!");
        }
        else{
            accountDB.setLogin(account.getLogin());
            accountDB.setPassword(account.getPassword());
            accountRepository.save(accountDB);
        }
        return accountDB;
    }
}
