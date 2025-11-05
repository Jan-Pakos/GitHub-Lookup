package com.githublookup.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubBranchDto(
        @JsonProperty("name")
        String branchName,
        Commit commit
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Commit(
            @JsonProperty("sha")
            String sha
    ){}
}
