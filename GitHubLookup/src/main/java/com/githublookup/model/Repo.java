package com.githublookup.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "owner", "name"})
@Table(name = "repo")
public class Repo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    String owner;

    @Getter
    @Setter
    @Column(nullable = false)
    String name;

    public Repo(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public Repo(Long id, String owner, String name) {
        this.id = id;
        this.owner = owner;
        this.name = name;
    }

}
