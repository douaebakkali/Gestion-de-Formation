package com.example.demo.Mapper;

import com.example.demo.dto.AvancementDTO;
import com.example.demo.entities.Avancement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvancementMapper {

    @Autowired
    private ParticipantMapper participantMapper;

    @Autowired
    private FormationMapper formationMapper;

    public AvancementDTO toDTO(Avancement avancement) {
        AvancementDTO dto = new AvancementDTO();
        dto.setId(avancement.getId());
        dto.setParticipant(participantMapper.toDTO(avancement.getParticipant()));
        dto.setFormation(formationMapper.toDTO(avancement.getFormation()));
        return dto;
    }

    public Avancement toEntity(AvancementDTO dto) {
        Avancement avancement = new Avancement();
        avancement.setId(dto.getId());
        avancement.setParticipant(participantMapper.toEntity(dto.getParticipant()));
        avancement.setFormation(formationMapper.toEntity(dto.getFormation()));
        return avancement;
    }
}
