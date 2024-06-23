package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionFormationDTO {
    private Long id;
    private Long formationId;
    private Long formateurId;
    private LocalDate dateSession;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Collection<Long> inscriptionIds; // Assuming Inscription IDs
}
