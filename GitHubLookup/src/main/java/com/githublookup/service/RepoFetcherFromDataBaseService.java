package com.githublookup.service;

import com.githublookup.error.UserNotFoundInDBException;
import com.githublookup.model.Repo;
import com.githublookup.repository.DataRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepoFetcherFromDataBaseService {

    DataRepository repository;

    public RepoFetcherFromDataBaseService(DataRepository repository) {
        this.repository = repository;
    }

    public List<Repo> getReposByUserName(String userName) {
        List<Repo> byOwner = repository.findByOwner(userName);
        if (byOwner.isEmpty()) {
            throw new UserNotFoundInDBException("User not found in database");
        }
        return byOwner;
    }

    public List<Repo> getAllRepos() {
        return repository.findAll();
    }


}
