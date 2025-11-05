package com.githublookup.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RepoPatchRequestDto(

        String newOwner,

        String newRepoName
) {
}
