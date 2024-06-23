package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Admin {
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

    public Admin() {
        this.setRole(ERole.ADMIN);
    }

    @PrePersist
    private void prePersist() {
        this.setRole(ERole.ADMIN);
    }

    public void setUser(Utilisateur user){
        this.idUser=user.getId();
    }
}
