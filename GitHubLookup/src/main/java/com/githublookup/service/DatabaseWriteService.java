package com.githublookup.service;

import com.githublookup.dto.request.RepoPostRequestDto;
import com.githublookup.model.Repo;
import com.githublookup.repository.DataRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class DatabaseWriteService {

    private final DataRepository repository;

    public DatabaseWriteService(DataRepository repository) {
        this.repository = repository;
    }

    public Repo addNewRepo(Repo repo) {
        log.info("Saving new repo to database: " + repo.toString());
        return repository.save(repo);
    }

    public void addNewRepos(List<Repo> reposToSave) {
        reposToSave.forEach(repo -> {
            repository.save(repo);
        });
    }
}
