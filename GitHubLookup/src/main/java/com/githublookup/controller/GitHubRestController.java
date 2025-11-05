package com.githublookup.controller;

import com.githublookup.dto.*;
import com.githublookup.dto.request.RepoPatchRequestDto;
import com.githublookup.dto.request.RepoPostRequestDto;
import com.githublookup.dto.request.RepoPutRequestDto;
import com.githublookup.dto.response.*;
import com.githublookup.model.Repo;

import com.githublookup.service.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@RestController
@Log4j2
@RequestMapping("/users")
@AllArgsConstructor
public class GitHubRestController {


    private final GitHubFetchReposService gitHubFetchReposService;
    private final GitHubBranchesGetterService gitHubBranchesGetterService;
    private final RepoFetcherFromDataBaseService repoFetcherFromDataBaseService;
    private final DatabaseWriteService databaseWriteService;
    private final RepoUpdater repoUpdater;
    private final RepoImporterService repoImporterService;



    @GetMapping("/database/import/{userName}")
    public List<PostRepoResponseDto> getBranchesFromReposOfUser(@PathVariable String userName) {
        List<GithubRepoDto> repos = gitHubFetchReposService.fetchReposFromUser(userName);
        List<RepositoryDetailsResponseDto> branches = gitHubBranchesGetterService.getNonForkReposWithBranches(repos).join();
        List<Repo> reposToSave = RepoMapper.mapFromRepositoryDetailsResponseDtoRepo(branches, userName);
        List<Repo> importedRepos = repoImporterService.importRepos(reposToSave);
        List<PostRepoResponseDto> responseDtos = RepoMapper.mapFromRepoToPostResponseDto(importedRepos);
        return responseDtos;
    }

    @GetMapping("/database/{userName}")
    public ResponseEntity<GetAllReposFromDatabaseResponseDto> getUserRepoByIdFromDatabase(@PathVariable String userName) {
        List<Repo> reposByUserName = repoFetcherFromDataBaseService.getReposByUserName(userName);
        GetAllReposFromDatabaseResponseDto responseDto = RepoMapper.mapFromRepoToGetAllReposFromDatabaseResponseDto(reposByUserName);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/database")
    public ResponseEntity<GetAllReposFromDatabaseResponseDto> getAllUserReposFromDatabase() {
        List<Repo> allReposFromDatabase = repoFetcherFromDataBaseService.getAllRepos();
        GetAllReposFromDatabaseResponseDto responseDto = RepoMapper.mapFromRepoToGetAllReposFromDatabaseResponseDto(allReposFromDatabase);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<PostRepoResponseDto> saveUserReposToDatabase(@RequestBody @Valid RepoPostRequestDto repoDto) {
        Repo repoToSave = RepoMapper.mapFromRepoDtoToRepo(repoDto);
        Repo savedRepo = databaseWriteService.addNewRepo(repoToSave);
        log.info("This was returned from the repository: " + savedRepo.toString());
        PostRepoResponseDto responseDto = RepoMapper.mapFromRepoToCreateRepoResponseDto(savedRepo);
        return ResponseEntity.ok(responseDto);
    }


    @PatchMapping ("/database/{id}")
    public ResponseEntity<PatchRepoResponseDto> updateGitHubRepoById(@PathVariable Long id, @RequestBody RepoPatchRequestDto repoPatchRequestDto) {
        Repo repoToUpdate = RepoMapper.mapFromRepoPatchRequestDtoToRepo(repoPatchRequestDto);
        Repo updatedRepo = repoUpdater.patchRepoById(id, repoToUpdate);
        PatchRepoResponseDto responseDto = RepoMapper.mapFromRepoToPatchRepoResponseDto(updatedRepo);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/database/{id}")
    public ResponseEntity<RepoFromDatabaseResponseDto> replaceGitHubRepoById(@PathVariable Long id, @RequestBody RepoPutRequestDto repoPutRequestDto) {
        Repo repoToUpdate = RepoMapper.mapFromRepoPutRequestDtoToRepo(repoPutRequestDto);
        Repo updatedRepo = repoUpdater.replaceRepoById(id, repoToUpdate);
        RepoFromDatabaseResponseDto responseDto = RepoMapper.mapFromRepoToPutRepoResponseDto(updatedRepo);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/database/{id}")
    public ResponseEntity<RepoFromDatabaseResponseDto> deleteRepoById(@PathVariable Long id) {
        Repo deletedRepo = repoUpdater.deleteRepoById(id);
        RepoFromDatabaseResponseDto responseDto = RepoMapper.mapFromRepoToPutRepoResponseDto(deletedRepo);
        return ResponseEntity.ok(responseDto);
    }




}
