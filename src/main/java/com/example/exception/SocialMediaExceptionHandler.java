package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SocialMediaExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAccountCreationCredentialsException.class)
    public void handleInvalidAccountCreationCredentialsException() {
        // Nothing to do here - the logic is handled by annotations
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateAccountException.class)
    public void handleDuplicateAccountException() {
        // Nothing to do here - the logic is handled by annotations
    }
}
