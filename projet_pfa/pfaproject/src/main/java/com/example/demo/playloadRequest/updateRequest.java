package com.example.demo.playloadRequest;

import lombok.Data;

@Data

public class updateRequest {
    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String email;
}
