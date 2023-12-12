package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    /**
     * Finds and returns the Account with a matching username (if it exists)
     * 
     * @param username the username of the Account to find
     * @return the Account with a matching username (if it exists)
     */
    public Account findAccountByUsername(String username);

    /**
     * Finds and returns the Account with a matching username and password (if it exists)'
     * '
     * @param username the username of the Account to find
     * @param password the password of the Account to find
     * @return the Account with a matching username and password (if it exists)
     */
    public Account findAccountByUsernameAndPassword(String username, String password);
}
