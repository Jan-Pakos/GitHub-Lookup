package com.githublookup.dto.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RepoPostRequestDto(

        @NotNull(message = "Username must not be null")
        @NotEmpty(message = "Username must not be empty")
        String username,

        @NotNull(message = "Repository name must not be null")
        @NotEmpty(message = "Repository name must not be empty")
        String repositoryName
) {
}
