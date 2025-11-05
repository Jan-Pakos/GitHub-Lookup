package com.githublookup.dto.response;

public record PatchRepoResponseDto(
        Long id,
        String updatedOwner,
        String updatedRepoName
) {
}
