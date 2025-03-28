package com.funmi.exceptions;

public class EmailCannotBeChangedException extends RuntimeException {
    public EmailCannotBeChangedException(String message) {
        super(message);
    }
}
