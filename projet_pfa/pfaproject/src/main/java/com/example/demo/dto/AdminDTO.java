package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor



public class AdminDTO {
    private Long id;
    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String email;
    private String role;
}
