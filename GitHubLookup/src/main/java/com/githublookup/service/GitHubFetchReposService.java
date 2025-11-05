package com.githublookup.service;

import com.githublookup.client.GitHubClient;
import com.githublookup.dto.GithubRepoDto;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GitHubFetchReposService {

    private final GitHubClient gitHubClient;

    public GitHubFetchReposService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    public List<GithubRepoDto> fetchReposFromUser(String userName) {
        return gitHubClient.getRepositories(userName);
    }
}
