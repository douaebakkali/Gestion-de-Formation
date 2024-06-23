package com.example.demo.Repository;

import com.example.demo.entities.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FormateurRepository extends JpaRepository<Formateur, Long> {
    List<Formateur> getFormateurByDateDebutDisponibiliteAndDateFinDisponibilite(
            LocalDate dateDebutDisponibilite, LocalDate dateFinDisponibilite);
    Optional<Formateur> findByEmail(String email);
    Optional<Formateur> findByUsername(String username);
}
