package com.githublookup.dto.request;

public record RepoPutRequestDto(

//        @NotEmpty(message = "owner must not be empty")
        String owner,

//        @NotEmpty(message = "name must not be empty")
        String name
) {
}
