package com.githublookup.dto.response;

import com.githublookup.dto.GitHubBranchDto;

import java.util.List;

public record RepositoryDetailsResponseDto(
        String repositoryName,
        String ownerLogin,
        List<GitHubBranchDto> branches

) {
}
