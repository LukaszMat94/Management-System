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
                .orElseThrow(() -> new DataNotFoundException(String.format("Get failed! Account not exist in database with id: %d", id)));
        return account;
    }

    @Transactional
    public Account addAccount(Account account){
        boolean accountExist = accountRepository
                .findByLogin(account.getLogin())
                .isPresent();

        if(accountExist){
            throw new DataDuplicateException(String.format("Add failed! Account already exist with login: %s" ,account.getLogin()));
        }
        else {
            Account addedAccount = accountRepository.save(account);
            return addedAccount;
        }
    }

    @Transactional
    public void deleteAccount(Integer id){
        if(accountRepository.existsById(id)){
            accountRepository.deleteById(id);
        }
        else {
            throw new DataNotFoundException(String.format("Delete failed! Account with id: %d not exist in database!", id));
        }
    }

    @Transactional
    public Account updateAccount(Account account, Integer id) throws Exception {
        Account accountDB = accountRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(String.format("Update failed! Account with id: %d not exist in database!", id)));

        boolean otherAccountWithSameLogin = accountRepository
                .findByLoginAndIdAccountNot(account.getLogin(), id)
                .isPresent();
        if(otherAccountWithSameLogin){
            throw new DataDuplicateException(String.format("Update failed! Account with login: %s already exist in database!", account.getLogin()));
        }
        else{
            accountDB.setLogin(account.getLogin());
            accountDB.setPassword(account.getPassword());
            accountRepository.save(accountDB);
        }
        return accountDB;
    }
}
