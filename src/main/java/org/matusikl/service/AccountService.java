package org.matusikl.service;

import org.matusikl.dto.accountdto.AccountGetDto;
import org.matusikl.dto.accountdto.AccountPostDto;
import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.exception.DataRelationException;
import org.matusikl.mapperinterface.AccountIMapper;
import org.matusikl.model.Account;
import org.matusikl.repository.AccountRepository;
import org.matusikl.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    AccountRepository accountRepository;
    EmployeeRepository employeeRepository;
    AccountIMapper accountIMapper;
    private Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          EmployeeRepository employeeRepository,
                          AccountIMapper accountIMapper) {
        this.accountRepository = accountRepository;
        this.employeeRepository = employeeRepository;
        this.accountIMapper = accountIMapper;
    }

    public AccountGetDto getAccount(Integer id) {
        logger.debug("In AccountService getAccount() id: {}", id);
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> {
                    DataNotFoundException exception = new DataNotFoundException(String.format("Get failed! Account not exist in database with id: %d", id));
                    logger.error("Exception occured in getAccount() id: {}", id, exception);
                    throw exception;
                });
        AccountGetDto accountDTO = accountIMapper.accountToAccountGetDto(account);
        logger.info("Found account with id {}", id);
        return accountDTO;
    }

    @Transactional
    public AccountGetDto addAccount(AccountPostDto accountPostDto) {
        logger.debug("In AccountService addAccount() account: {}", accountPostDto);
        boolean accountExist = accountRepository
                .findByLogin(accountPostDto.getLogin())
                .isPresent();
        if (accountExist) {
            DataDuplicateException exception = new DataDuplicateException(String.format("Add failed! Account already exist with login: %s", accountPostDto.getLogin()));
            logger.error("Exception occured in addAccount() account: {}", accountPostDto, exception);
            throw exception;
        } else {
            Account accountDB = accountRepository.save(accountIMapper.accountPostDtoToAccount(accountPostDto));
            AccountGetDto accountGetDto = accountIMapper.accountToAccountGetDto(accountDB);
            logger.info("Account: {} added successfully", accountGetDto);
            return accountGetDto;
        }
    }

    @Transactional
    public void deleteAccount(Integer id) {
        logger.debug("In AccountService deleteAccount() id: {}", id);
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> {
                    DataNotFoundException exception = new DataNotFoundException(String.format("Cannot delete account with id: %d because this not exist in database", id));
                    logger.error("Exception occured in deleteAccount() id: {} ", id, exception);
                    throw exception;
                });
        if(employeeRepository.existsByAccountEmployee(account)){
            DataRelationException exception = new DataRelationException(String.format("Delete failed! First you need to detach Employee from this Account id: %d", id));
            logger.error("Exception occured in deleteAccount() id: {} account not detached", id, exception);
            throw exception;
        }
        else{
            accountRepository.deleteById(id);
            logger.info("Account id: {} deleted successfully", id);
        }
    }

    @Transactional
    public AccountGetDto updateAccount(AccountPostDto accountPostDto, Integer id) {
        logger.debug("In AccountService updateAccount() account: {} id: {}", accountPostDto, id);
        Account account = accountIMapper.accountPostDtoToAccount(accountPostDto);
        Account accountDB = accountRepository
                .findById(id)
                .orElseThrow(() -> {
                    DataNotFoundException exception = new DataNotFoundException(String.format("Update failed! Account with id: %d not exist in database!", id));
                    logger.error("Error occured in updateAccount() findById() account: {} id: {}", account, id, exception);
                    throw exception;
                });

        boolean otherAccountWithSameLogin = accountRepository
                .findByLoginAndIdAccountNot(account.getLogin(), id)
                .isPresent();

        if (otherAccountWithSameLogin) {
            DataDuplicateException exception = new DataDuplicateException(String.format("Update failed! Account with login: %s already exist in database!", account.getLogin()));
            logger.error("Error occured in updateAccount() findByLoginAndIdAccountNot() account: {} id: {}", accountDB, id, exception);
            throw exception;
        } else {
            try {
                accountIMapper.updateAccountFromAccountPostDto(accountPostDto, accountDB);
                accountRepository.save(accountDB);
                logger.info("Account: {} id {} updated successfully", accountDB, id);
            } catch (Exception exception) {
                logger.error("Exception occured in updateAccount() account: {} id: {} , method set login/password", accountDB, id, exception);
            }
        }
        AccountGetDto accountGetDto = accountIMapper.accountToAccountGetDto(accountDB);
        return accountGetDto;
    }
}
