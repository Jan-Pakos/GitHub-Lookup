package com.githublookup.client;

import com.githublookup.config.FeignConfig;
import com.githublookup.dto.GitHubBranchDto;
import com.githublookup.dto.GithubRepoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "githubClient",
        url = "https://api.github.com",
        configuration = FeignConfig.class
)
public interface GitHubClient {

    @GetMapping(value = "/users/{userName}/repos",
                headers = "Accept=application/vnd.github+json")
    List<GithubRepoDto> getRepositories(@PathVariable String userName);

    @GetMapping(value = "/repos/{owner}/{repo}/branches",
            headers = "Accept=application/vnd.github+json")
    List<GitHubBranchDto> getBranchesFromRepo(@PathVariable String owner,
                                              @PathVariable String repo);
}
