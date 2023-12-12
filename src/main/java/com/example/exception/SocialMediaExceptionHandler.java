package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SocialMediaExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateAccountException.class)
    /**
     * Does nothing - the application will set the HTTP status to 409 if this is called.
     */
    public void handleDuplicateAccountException() {
        // Nothing to do here - the logic is handled by annotations
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAccountCreationCredentialsException.class)
    /**
     * Does nothing - the application will set the HTTP status to 400 if this is called.
     */
    public void handleInvalidAccountCreationCredentialsException() {
        // Nothing to do here - the logic is handled by annotations
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidAccountLoginCredentialsException.class)
    /**
     * Does nothing - the application will set the HTTP status to 401 if this is called.
     */
    public void handleInvalidAccountLoginCredentialsException() {
        // Nothing to do here - the logic is handled by annotations
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidMessageLengthException.class)
    /**
     * Does nothing - the application will set the HTTP status to 400 if this is called.
     */
    public void handleInvalidMessageLengthException() {
        // Nothing to do here - the logic is handled by annotations
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NonexistentMessageSenderException.class)
    /**
     * Does nothing - the application will set the HTTP status to 400 if this is called.
     */
    public void handleNonexistentMessageSenderException() {
        // Nothing to do here - the logic is handled by annotations
    }
}
