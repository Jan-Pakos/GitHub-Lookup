package com.githublookup.error;

public class UserNotFoundInDBException extends RuntimeException {
    public UserNotFoundInDBException(String message) {
        super(message);
    }
}
