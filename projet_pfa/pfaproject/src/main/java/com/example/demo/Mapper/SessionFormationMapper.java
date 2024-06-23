package com.example.demo.Mapper;

import com.example.demo.dto.SessionFormationDTO;
import com.example.demo.entities.Formateur;
import com.example.demo.entities.Formation;
import com.example.demo.entities.Inscription;
import com.example.demo.entities.SessionFormation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class SessionFormationMapper {

    public SessionFormationDTO toDTO(SessionFormation sessionFormation) {
        SessionFormationDTO dto = new SessionFormationDTO();
        dto.setId(sessionFormation.getId());
        dto.setFormationId(sessionFormation.getFormation().getId());
        dto.setFormateurId(sessionFormation.getFormateur().getId());
        dto.setDateSession(sessionFormation.getDateSession());
        dto.setHeureDebut(sessionFormation.getHeureDebut());
        dto.setHeureFin(sessionFormation.getHeureFin());
        dto.setInscriptionIds(sessionFormation.getInscriptions().stream()
                .map(inscription -> inscription.getId())
                .collect(Collectors.toList()));
        return dto;
    }

    public SessionFormation toEntity(SessionFormationDTO dto, Formation formation, Formateur formateur, Collection<Inscription> inscriptions) {
        SessionFormation sessionFormation = new SessionFormation();
        sessionFormation.setId(dto.getId());
        sessionFormation.setFormation(formation);
        sessionFormation.setFormateur(formateur);
        sessionFormation.setDateSession(dto.getDateSession());
        sessionFormation.setHeureDebut(dto.getHeureDebut());
        sessionFormation.setHeureFin(dto.getHeureFin());
        sessionFormation.setInscriptions(inscriptions);
        return sessionFormation;
    }
}

