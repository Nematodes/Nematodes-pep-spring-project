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

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account addAccount(Account account) {
        if (account.getUsername().length() == 0)
        {
            throw new InvalidAccountCreationCredentialsException("Account usernames cannot be blank!");
        }

        if (account.getPassword().length() < 4)
        {
            throw new InvalidAccountCreationCredentialsException("Passwords must be at least 4 characters in length!");
        }

        if (accountRepository.findAccountByUsername(account.getUsername()) != null)
        {
            throw new DuplicateAccountException();
        }

        return accountRepository.save(account);
    }

    public Account loginToAccount(Account account) {
        Account retrievedAccount = accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());

        if (retrievedAccount == null)
        {
            // Do not bother revealing whether or not it was the username or password that was incorrect
            throw new InvalidAccountLoginCredentialsException();
        }

        return retrievedAccount;
    }
}
