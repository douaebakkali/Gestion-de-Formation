package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor

public class Formateur{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String email;

    @Enumerated(EnumType.STRING)
    private ERole role;

    private Long idUser;

    private String competences;
    private String experiencesProfessionnelles;
    private LocalDate dateDebutDisponibilite;
    private LocalTime heureDebutDisponibilite;
    private LocalDate dateFinDisponibilite;
    private LocalTime heureFinDisponibilite;


    @OneToMany(mappedBy="formateur")
    private Collection<SessionFormation> sessions= new ArrayList<>();

    public Formateur() {
        this.setRole(ERole.FORMATEUR);
    }

    @PrePersist
    private void prePersist() {
        this.setRole(ERole.FORMATEUR);
    }

    public void setUser(Utilisateur user){
        this.idUser=user.getId();
    }
}
