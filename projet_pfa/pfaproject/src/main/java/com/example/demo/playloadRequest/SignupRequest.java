package com.example.demo.playloadRequest;
import com.example.demo.entities.ERole;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data

public class SignupRequest {

    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String email;
    private ERole role;
    private String competence;
    private String competences;
    private String experiencesProfessionnelles;
    private LocalDate dateDebutDisponibilite;
    private LocalTime heureDebutDisponibilite;
    private LocalDate dateFinDisponibilite;
    private LocalTime heureFinDisponibilite;
}