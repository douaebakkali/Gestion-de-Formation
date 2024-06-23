package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormationDTO {
    private Long id;
    private String titre;
    private Long IdDomaine;
    private String descriptionFormation;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Collection<Long> sessionIds; // Assuming SessionFormation IDs
    private Collection<Long> avancementIds; // Assuming Avancement IDs
}
