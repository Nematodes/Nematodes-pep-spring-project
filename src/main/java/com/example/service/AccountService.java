package com.example.service;

import com.example.entity.Account;
import com.example.exception.*;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {
    private AccountRepository accountRepository;

    /**
     * Creates an AccountService that uses a specific AccountRepository
     * 
     * @param accountRepository the AccountRepository to use
     */
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Sets the AccountRepository for this AccountService
     * 
     * @param accountRepository the AccountRepository to set
     */
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Adds an account to the application's account database
     * 
     * This will fail if the account's username has a length of 0 characters, the account's
     * password has a length of less than 4 characters, or if an account with the same username
     * already exists in the database.
     * 
     * @param account the Account to store, without its account_id set
     * @return the same Account with its account_id set to an automatically-generated value
     */
    public Account addAccount(Account account) {
        if (account.getUsername().length() == 0) {
            throw new InvalidAccountCreationCredentialsException("Account usernames cannot be blank!");
        }

        if (account.getPassword().length() < 4) {
            throw new InvalidAccountCreationCredentialsException("Passwords must be at least 4 characters in length!");
        }

        if (accountRepository.findAccountByUsername(account.getUsername()) != null) {
            throw new DuplicateAccountException();
        }

        return accountRepository.save(account);
    }

    /**
     * Returns an Account with matching credentials (username and password) if it exists in the
     * application's account database
     * 
     * This will fail if a matching account is not found.
     * 
     * @param account the Account to check for the matching credentials of
     * @return the Account with matching credentials
     */
    public Account loginToAccount(Account account) {
        Account retrievedAccount = accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());

        if (retrievedAccount == null) {
            // Do not bother revealing whether or not it was the username or password that was incorrect
            throw new InvalidAccountLoginCredentialsException();
        }

        return retrievedAccount;
    }
}
