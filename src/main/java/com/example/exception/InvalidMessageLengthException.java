package com.example.exception;

public class InvalidMessageLengthException extends RuntimeException {
    public InvalidMessageLengthException() {
        super();
    }

    public InvalidMessageLengthException(String message) {
        super(message);
    }
}
