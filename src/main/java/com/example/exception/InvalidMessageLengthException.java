package com.example.exception;

public class InvalidMessageLengthException extends RuntimeException {
    /**
     * Creates an InvalidMessageLengthException
     */
    public InvalidMessageLengthException() {
        super();
    }

    /**
     * Creates an InvalidMessageLengthException with a message (text) included with it
     * 
     * @param message the message to include with the exception
     */
    public InvalidMessageLengthException(String message) {
        super(message);
    }
}
