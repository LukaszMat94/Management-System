package org.matusikl.service;

import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.model.Account;
import org.matusikl.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    AccountRepository accountRepository;
    private Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account getAccount(Integer id){
        logger.debug("In AccountService getAccount() method");
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> {
                    DataNotFoundException exception = new DataNotFoundException(String.format("Get failed! Account not exist in database with id: %d", id));
                    logger.error("Exception occured in getAccount: {} ", exception);
                    return exception;
                });
        logger.info("Found account with id {}", id);
        return account;
    }

    @Transactional
    public Account addAccount(Account account){
        logger.debug("In AccountService addAccount() method");
        boolean accountExist = accountRepository
                .findByLogin(account.getLogin())
                .isPresent();
        if(accountExist){
            DataDuplicateException exception = new DataDuplicateException(String.format("Add failed! Account already exist with login: %s" ,account.getLogin()));
            logger.error("Exception occured in addAccount: {}", exception);
            throw exception;
        }
        else {
            Account addedAccount = accountRepository.save(account);
            logger.info("Account {} added successfully", account);
            return addedAccount;
        }
    }

    @Transactional
    public void deleteAccount(Integer id){
        logger.debug("In AccountService deleteAccount() method");
        if(accountRepository.existsById(id)){
            accountRepository.deleteById(id);
            logger.info("Account id {} deleted successfully", id);
        }
        else {
            DataNotFoundException exception = new DataNotFoundException(String.format("Delete failed! Account with id: %d not exist in database!", id));
            logger.error("Exception occured in deleteAccount id: {} ", id, exception);
            throw exception;
        }
    }

    @Transactional
    public Account updateAccount(Account account, Integer id){
        logger.debug("In AccountService updateAccount() method");
        Account accountDB = accountRepository
                .findById(id)
                .orElseThrow(() -> {
                            DataNotFoundException exception = new DataNotFoundException(String.format("Update failed! Account with id: %d not exist in database!", id));
                            logger.error("Error occured in update account findById()", exception);
                            throw exception;
                });

        boolean otherAccountWithSameLogin = accountRepository
                .findByLoginAndIdAccountNot(account.getLogin(), id)
                .isPresent();
        if(otherAccountWithSameLogin){
            DataDuplicateException exception = new DataDuplicateException(String.format("Update failed! Account with login: %s already exist in database!", account.getLogin()));
            logger.error("Error occured in update account findByLoginAndIdAccountNot method", exception);
            throw new DataDuplicateException(String.format("Update failed! Account with login: %s already exist in database!", account.getLogin()));
        }
        else{
            try {
                accountDB.setLogin(account.getLogin());
                accountDB.setPassword(account.getPassword());
                accountRepository.save(accountDB);
                logger.info("Account id {} updated successfully", id);
            }
            catch(Exception exception){
                logger.error("Exception occured in update account set login/password", exception);
            }
        }
        return accountDB;
    }
}
