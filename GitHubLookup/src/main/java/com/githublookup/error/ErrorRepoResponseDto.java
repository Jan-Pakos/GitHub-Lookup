package com.githublookup.error;

import org.springframework.http.HttpStatus;

public record ErrorRepoResponseDto(String message, HttpStatus status) {
}
