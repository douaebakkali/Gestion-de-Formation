package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor

public class Participant{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String email;

    private Long idUser;

    @Enumerated(EnumType.STRING)
    private ERole role;

    private String competence;

    public Participant() {
        this.setRole(ERole.PARTICIPANT);
    }

    @PrePersist
    private void prePersist() {
        this.setRole(ERole.PARTICIPANT);
    }

    @OneToMany(mappedBy="participant")
    private Collection<Inscription> inscriptions=new ArrayList<>();


    @OneToMany(mappedBy = "participant")
    private Collection<Avancement> avancements=new ArrayList<>();

   public void setUser(Utilisateur user) {
        this.idUser = user.getId();
   }

}
