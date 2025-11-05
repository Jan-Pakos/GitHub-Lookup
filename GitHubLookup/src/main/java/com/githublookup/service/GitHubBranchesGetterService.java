package com.githublookup.service;

import com.githublookup.client.GitHubClient;
import com.githublookup.dto.GitHubBranchDto;
import com.githublookup.dto.GithubRepoDto;
import com.githublookup.dto.response.RepositoryDetailsResponseDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GitHubBranchesGetterService {

    private final GitHubClient gitHubClient;


    public GitHubBranchesGetterService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    @Async
    public CompletableFuture<List<RepositoryDetailsResponseDto>> getNonForkReposWithBranches(List<GithubRepoDto> repos) {
        List<RepositoryDetailsResponseDto> futures = repos.stream()
                .filter(repo -> !repo.fork())
                .map(repo -> {
                        List<GitHubBranchDto> branches = gitHubClient.getBranchesFromRepo(repo.owner().login(), repo.repoName());
                        return new RepositoryDetailsResponseDto(repo.repoName(), repo.owner().login(), branches);
            }).toList();
        return CompletableFuture.completedFuture(futures);
    }
}
