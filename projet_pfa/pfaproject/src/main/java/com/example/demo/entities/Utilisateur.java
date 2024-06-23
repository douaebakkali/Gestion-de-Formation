package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
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

    public Utilisateur(String username, String password, String nom, String prenom, String email){
        this.username=username;
        this.password=password;
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
    }
}
