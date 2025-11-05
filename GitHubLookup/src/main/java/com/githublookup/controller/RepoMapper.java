package com.githublookup.controller;

import com.githublookup.dto.response.*;
import com.githublookup.dto.request.RepoPatchRequestDto;
import com.githublookup.dto.request.RepoPutRequestDto;
import com.githublookup.dto.request.RepoPostRequestDto;
import com.githublookup.model.Repo;
import jakarta.validation.Valid;

import java.util.List;

public class RepoMapper {

    public static Repo mapFromRepoDtoToRepo(RepoPostRequestDto repoDto) {
        return new Repo(repoDto.username(), repoDto.repositoryName());
    }

    public static PostRepoResponseDto mapFromRepoToCreateRepoResponseDto(Repo savedRepo) {
        return new PostRepoResponseDto(savedRepo.getId(), savedRepo.getOwner(), savedRepo.getName());
    }

    public static GetAllReposFromDatabaseResponseDto mapFromRepoToGetAllReposFromDatabaseResponseDto(List<Repo> allReposFromDatabase) {
       return new GetAllReposFromDatabaseResponseDto(allReposFromDatabase);
    }

    public static Repo mapFromRepoPatchRequestDtoToRepo(RepoPatchRequestDto repoPatchRequestDto) {
        return new Repo(repoPatchRequestDto.newOwner(),repoPatchRequestDto.newRepoName());
    }

    public static PatchRepoResponseDto mapFromRepoToPatchRepoResponseDto(Repo updatedRepo) {
        return new PatchRepoResponseDto(updatedRepo.getId(), updatedRepo.getOwner(), updatedRepo.getName());
    }

    public static Repo mapFromRepoPutRequestDtoToRepo(@Valid RepoPutRequestDto repoPutRequestDto) {
        return new Repo(repoPutRequestDto.owner(), repoPutRequestDto.name());
    }

    public static RepoFromDatabaseResponseDto mapFromRepoToPutRepoResponseDto(Repo updatedRepo) {
        return new RepoFromDatabaseResponseDto(updatedRepo.getId(), updatedRepo.getOwner(), updatedRepo.getName());
    }

    public static List<Repo> mapFromRepositoryDetailsResponseDtoRepo(List<RepositoryDetailsResponseDto> branches, String userName) {
        return branches.stream()
                .map(repo -> new Repo(userName, repo.repositoryName()))
                .toList();
    }

    public static List<PostRepoResponseDto> mapFromRepoToPostResponseDto(List<Repo> importedRepos) {
        return importedRepos.stream()
                .map(repo -> new PostRepoResponseDto(repo.getId(), repo.getOwner(), repo.getName()))
                .toList();
    }
}
