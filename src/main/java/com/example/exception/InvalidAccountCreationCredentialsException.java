package com.example.exception;

public class InvalidAccountCreationCredentialsException extends RuntimeException {
    /**
     * Creates an InvalidAccountCreationCredentialsException
     */
    public InvalidAccountCreationCredentialsException() {
        super();
    }

    /**
     * Creates an InvalidAccountCreationCredentialsException with a message (text) included with it
     * 
     * @param message the message to include with the exception
     */
    public InvalidAccountCreationCredentialsException(String message) {
        super(message);
    }
}
