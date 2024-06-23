package com.example.demo.Mapper;

import com.example.demo.dto.DomaineDTO;
import com.example.demo.entities.Domaine;
import org.springframework.stereotype.Component;

@Component
public class DomaineMapper {

    private final FormationMapper formationMapper;

    public DomaineMapper(FormationMapper formationMapper) {
        this.formationMapper = formationMapper;
    }

    public DomaineDTO toDTO(Domaine domaine) {
        if (domaine == null) {
            return null;
        }

        DomaineDTO dto = new DomaineDTO();
        dto.setId(domaine.getId());
        dto.setLibelle(domaine.getLibelle());

        /*Set<FormationDTO> formationDTOs = domaine.getFormations().stream()
                .map(formationMapper::toDTO)
                .collect(Collectors.toSet());
        dto.setFormations(formationDTOs);*/

        return dto;
    }

    public Domaine toEntity(DomaineDTO dto) {
        if (dto == null) {
            return null;
        }

        Domaine domaine = new Domaine();
        domaine.setId(dto.getId());
        domaine.setLibelle(dto.getLibelle());

        return domaine;
    }
}
