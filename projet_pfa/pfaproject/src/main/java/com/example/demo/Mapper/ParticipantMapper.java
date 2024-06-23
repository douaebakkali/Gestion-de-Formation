package com.example.demo.Mapper;

import com.example.demo.dto.ParticipantDTO;
import com.example.demo.entities.ERole;
import com.example.demo.entities.Participant;
import org.springframework.stereotype.Component;

@Component
public class ParticipantMapper {
    public ParticipantDTO toDTO(Participant participant) {

        ParticipantDTO dto = new ParticipantDTO();
        dto.setId(participant.getId());
        dto.setUsername(participant.getUsername());
        dto.setEmail(participant.getEmail());
        dto.setPassword(participant.getPassword());
        dto.setNom(participant.getNom());
        dto.setPrenom(participant.getPrenom());
        dto.setRole(participant.getRole().name()); // Convert Role enum to its string representation
        dto.setCompetence(participant.getCompetence());
        return dto;
    }

    public Participant toEntity(ParticipantDTO dto) {
        Participant participant = new Participant();
        participant.setUsername(dto.getUsername());
        participant.setPassword(dto.getPassword());
        participant.setNom(dto.getNom());
        participant.setPrenom(dto.getPrenom());
        participant.setEmail(dto.getEmail());
        participant.setRole(ERole.valueOf(dto.getRole())); // Convert string role to Role enum
        participant.setCompetence(dto.getCompetence());
        return participant;
    }
}
