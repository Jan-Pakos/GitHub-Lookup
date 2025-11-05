package com.githublookup.error;

public class ResourceNotFoundInDBException extends RuntimeException {
    public ResourceNotFoundInDBException(String message) {
        super(message);
    }
}
