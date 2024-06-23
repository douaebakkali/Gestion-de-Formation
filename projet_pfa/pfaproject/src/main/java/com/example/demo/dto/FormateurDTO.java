package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
/*
@Data
@EqualsAndHashCode(callSuper = true)
public class FormateurDTO extends UtilisateurDTO {
    private String competences;
    private String experiencesProfessionnelles;
    private LocalDate dateDebutDisponibilite;
    private LocalTime heureDebutDisponibilite;
    private LocalDate dateFinDisponibilite;
    private LocalTime heureFinDisponibilite;
}
*/

@Data
public class FormateurDTO {
    private Long id;

    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String email;
    private String role;
    private String competences;
    private String experiencesProfessionnelles;
    private LocalDate dateDebutDisponibilite;
    private LocalTime heureDebutDisponibilite;
    private LocalDate dateFinDisponibilite;
    private LocalTime heureFinDisponibilite;
}
