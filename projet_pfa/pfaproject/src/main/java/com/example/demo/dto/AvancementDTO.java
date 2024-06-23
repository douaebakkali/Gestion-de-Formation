package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AvancementDTO {

    private Long id;
    private ParticipantDTO participant;
    private FormationDTO formation;

    // Constructors, getters, and setters
}
