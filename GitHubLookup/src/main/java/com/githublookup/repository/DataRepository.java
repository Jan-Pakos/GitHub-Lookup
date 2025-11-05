package com.githublookup.repository;


import com.githublookup.model.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface DataRepository extends JpaRepository<Repo, Long> {

    // 1. Custom derived query - Keep!
    List<Repo> findByOwner(String owner);

    // 2. Custom derived query - Keep!
    boolean existsByOwnerAndName(String owner, String name);

    // 3. Custom native/modifying query - Keep!
    @Modifying
    @Query("UPDATE Repo r SET r.name = :#{#repo.name}, r.owner = :#{#repo.owner} WHERE r.id = :id")
    void updateById(@Param("id") Long id, @Param("repo") Repo repo);
}
