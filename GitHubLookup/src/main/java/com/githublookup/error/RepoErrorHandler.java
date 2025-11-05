package com.githublookup.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class RepoErrorHandler {

    @ExceptionHandler(WrongJsonBodyException.class)
    public ResponseEntity<ErrorRepoResponseDto> handleException(WrongJsonBodyException exception) {
        log.warn("JSON body is incorrect");
        ErrorRepoResponseDto errorSongResponseDto = new ErrorRepoResponseDto(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorSongResponseDto);
    }

    @ExceptionHandler(ResourceNotFoundInDBException.class)
    public ResponseEntity<ErrorRepoResponseDto> handleException(ResourceNotFoundInDBException exception) {
        log.warn("Resource not found in database");
        ErrorRepoResponseDto errorSongResponseDto = new ErrorRepoResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorSongResponseDto);
    }

    @ExceptionHandler(UserNotFoundInDBException.class)
    public ResponseEntity<ErrorRepoResponseDto> handleException(UserNotFoundInDBException exception) {
        log.warn("User not found in database");
        ErrorRepoResponseDto errorSongResponseDto = new ErrorRepoResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorSongResponseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRepoResponseDto> handleException(MethodArgumentNotValidException exception) {
        log.warn("Bad request body");
        ErrorRepoResponseDto errorSongResponseDto = new ErrorRepoResponseDto("Bad JSON body", HttpStatus.BAD_REQUEST);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorSongResponseDto);
    }

}
