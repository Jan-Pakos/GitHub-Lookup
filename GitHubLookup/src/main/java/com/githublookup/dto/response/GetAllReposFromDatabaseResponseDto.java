package com.githublookup.dto.response;

import com.githublookup.model.Repo;

import java.util.List;

public record GetAllReposFromDatabaseResponseDto(List<Repo> allReposFromDatabase) {
}
