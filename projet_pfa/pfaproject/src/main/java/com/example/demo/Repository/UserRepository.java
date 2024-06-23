package com.example.demo.Repository;

import com.example.demo.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Utilisateur,Long> {
    Optional<Utilisateur> findByEmail(String username);

    Optional<Utilisateur> findByUsername(String username);

    boolean existsByUsername(String username);
}
