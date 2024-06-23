package com.example.demo.Mapper;

import com.example.demo.Repository.ParticipantRepository;
import com.example.demo.Repository.SessionFormationRepository;
import com.example.demo.dto.InscriptionDTO;
import com.example.demo.entities.Inscription;
import com.example.demo.entities.Participant;
import com.example.demo.entities.SessionFormation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InscriptionMapper {

    @Autowired
    private SessionFormationRepository sessionFormationRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    public InscriptionDTO toDTO(Inscription inscription) {
        InscriptionDTO dto = new InscriptionDTO();
        dto.setId(inscription.getId());
        dto.setSessionId(inscription.getSessionFormation().getId());
        dto.setParticipantId(inscription.getParticipant().getId());
        dto.setAttended(inscription.isAttended());
        return dto;
    }

    public Inscription toEntity(InscriptionDTO dto) {
        Inscription inscription = new Inscription();
        inscription.setId(dto.getId());
        inscription.setSessionFormation(fetchSessionFormationById(dto.getSessionId()));
        inscription.setParticipant(fetchParticipantById(dto.getParticipantId()));
        inscription.setAttended(dto.isAttended());
        return inscription;
    }

    private SessionFormation fetchSessionFormationById(Long id) {
        return sessionFormationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SessionFormation not found with id: " + id));
    }

    private Participant fetchParticipantById(Long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Participant not found with id: " + id));
    }
}
