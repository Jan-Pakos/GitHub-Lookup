package com.githublookup.dto.response;

public record RepoFromDatabaseResponseDto(
        Long id,
        String owner,
        String name
) {
}
