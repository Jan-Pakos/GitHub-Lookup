package com.githublookup.error;


public record GlobalErrorDto(
        Integer status,
        String message
) {
}
