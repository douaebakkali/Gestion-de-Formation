package com.example.demo.dto;
/*
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ParticipantDTO extends UtilisateurDTO {
    private String competence;
}*/

import lombok.Data;

@Data
public class ParticipantDTO {
    private Long id;

    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String email;
    private String role;
    private String competence;
}

