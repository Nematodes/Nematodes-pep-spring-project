package com.example.exception;

public class InvalidAccountCreationCredentialsException extends RuntimeException {
    public InvalidAccountCreationCredentialsException()
    {
        super();
    }

    public InvalidAccountCreationCredentialsException(String message)
    {
        super(message);
    }
}
