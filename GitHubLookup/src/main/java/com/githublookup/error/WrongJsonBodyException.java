package com.githublookup.error;

public class WrongJsonBodyException extends RuntimeException {
    public WrongJsonBodyException(String message) {
        super(message);
    }
}
