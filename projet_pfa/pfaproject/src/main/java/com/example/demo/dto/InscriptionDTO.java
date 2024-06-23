package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class InscriptionDTO {
    private Long id;
    private Long sessionId;
    private Long participantId;
    private boolean attended;
}