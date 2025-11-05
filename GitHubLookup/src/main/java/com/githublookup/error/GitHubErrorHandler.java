package com.githublookup.error;

import com.githublookup.controller.GitHubRestController;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.MediaType;


@ControllerAdvice(assignableTypes = GitHubRestController.class)
public class GitHubErrorHandler {

    @ExceptionHandler(FeignException.NotFound.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GlobalErrorDto handleFeignNotFound(FeignException.NotFound ex) {
        return new GlobalErrorDto(HttpStatus.NOT_FOUND.value(), "User not found");
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<GlobalErrorDto> handleNotAcceptable(HttpMediaTypeNotAcceptableException ex) {
        GlobalErrorDto errorDto = new GlobalErrorDto(
                HttpStatus.NOT_ACCEPTABLE.value(),
                "Unsupported 'Accept' header. Only 'application/json' is supported."
        );

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDto);
    }
}
