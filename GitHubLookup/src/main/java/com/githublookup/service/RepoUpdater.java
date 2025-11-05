package com.githublookup.service;

import com.githublookup.error.ResourceNotFoundInDBException;
import com.githublookup.error.WrongJsonBodyException;
import com.githublookup.model.Repo;
import com.githublookup.repository.DataRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RepoUpdater {

    DataRepository dataRepository;

    public RepoUpdater(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public Repo patchRepoById(Long id, Repo repo) {
        Repo repoFromDatabase = dataRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundInDBException("Repo not found with id: " + id));
        if (repo.getName() != null) {
            repoFromDatabase.setName(repo.getName());
        }
        if (repo.getOwner() != null) {
            repoFromDatabase.setOwner(repo.getOwner());
        }
        dataRepository.updateById(id, repoFromDatabase);
        return repoFromDatabase;
    }

    public Repo replaceRepoById(Long id, Repo repoToUpdate) {
        Repo repoFromDatabase = dataRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundInDBException("Repo not found with id: " + id)
        );
        if (repoToUpdate.getName() == null) {
            throw new WrongJsonBodyException("Both name and owner fields are required");
        }
        if (repoToUpdate.getOwner() == null) {
            throw new WrongJsonBodyException("Both name and owner fields are required");
        }
        repoFromDatabase.setName(repoToUpdate.getName());
        repoFromDatabase.setOwner(repoToUpdate.getOwner());
        dataRepository.updateById(id,repoFromDatabase);
        return repoFromDatabase;
    }

    public Repo deleteRepoById(Long id) {
        Repo repoFromDatabase = dataRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundInDBException("Repo not found with id: " + id)
        );
        dataRepository.deleteById(id);
        return repoFromDatabase;
    }
}
