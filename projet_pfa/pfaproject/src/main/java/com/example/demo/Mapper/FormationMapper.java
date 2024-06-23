package com.example.demo.Mapper;

import com.example.demo.Repository.DomaineRepository;
import com.example.demo.dto.FormationDTO;
import com.example.demo.entities.Domaine;
import com.example.demo.entities.Formation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FormationMapper {

    @Autowired
    private DomaineRepository domaineRepository;

    public FormationDTO toDTO(Formation formation) {
        if (formation == null) {
            return null;
        }

        FormationDTO dto = new FormationDTO();
        dto.setId(formation.getId());
        dto.setTitre(formation.getTitre());
        dto.setDescriptionFormation(formation.getDescriptionFormation());
        dto.setDateDebut(formation.getDateDebut());
        dto.setDateFin(formation.getDateFin());

        // Mapping du domaine
        if (formation.getDomaine() != null) {
            dto.setIdDomaine(formation.getDomaine().getId());
        }
        // Mapping SessionFormation IDs
        dto.setSessionIds(formation.getSessions().stream()
                .map(session -> session.getId())
                .collect(Collectors.toList()));

        // Mapping Avancement IDs
        dto.setAvancementIds(formation.getAvancements().stream()
                .map(avancement -> avancement.getId())
                .collect(Collectors.toList()));
        return dto;

    }

    public Formation toEntity(FormationDTO dto) {
        if (dto == null) {
            return null;
        }

        Formation entity = new Formation();
        entity.setId(dto.getId());
        entity.setTitre(dto.getTitre());
        entity.setDescriptionFormation(dto.getDescriptionFormation());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());

        // Mapping du domaine
        if (dto.getIdDomaine() != null) {
            Domaine domaine = domaineRepository.findById(dto.getIdDomaine()).orElse(null);
            entity.setDomaine(domaine);
        }

        return entity;
    }
}
