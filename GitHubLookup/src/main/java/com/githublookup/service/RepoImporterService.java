package com.githublookup.service;

import com.githublookup.model.Repo;
import com.githublookup.repository.DataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepoImporterService {

    private final DataRepository dataRepository;

    public RepoImporterService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public List<Repo> importRepos(List<Repo> repos) {
        List<Repo> reposNotInDatabase = repos.stream().filter(repo ->
                !dataRepository.existsByOwnerAndName(repo.getOwner(), repo.getName())).toList();
        return dataRepository.saveAll(reposNotInDatabase);
    }


}
