package com.githublookup.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubRepoDto(
        @JsonProperty("name")
        String repoName,
        @JsonProperty("fork")
        Boolean fork,
        Owner owner
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Owner(
            @JsonProperty("login")
            String login
    ) {}
}
