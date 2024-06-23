package com.example.demo.Service.Impl;

import com.example.demo.Mapper.AvancementMapper;
import com.example.demo.Repository.AvancementRepository;
import com.example.demo.Service.AvancementService;
import com.example.demo.dto.AvancementDTO;
import com.example.demo.entities.Avancement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvancementServiceImpl implements AvancementService {

    @Autowired
    private AvancementRepository avancementRepository;

    @Autowired
    private AvancementMapper avancementMapper;

    @Override
    public List<AvancementDTO> getAllAvancements() {
        return avancementRepository.findAll().stream()
                .map(avancementMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AvancementDTO getAvancementById(Long id) {
        return avancementRepository.findById(id)
                .map(avancementMapper::toDTO)
                .orElse(null);
    }

    @Override
    public AvancementDTO getAvancement(Long participantId, Long formationId) {
        Avancement avancement = avancementRepository.findByParticipantIdAndFormationId(participantId, formationId)
                .orElseThrow(() -> new IllegalArgumentException("Avancement not found for participantId: " + participantId + " and formationId: " + formationId));

        return avancementMapper.toDTO(avancement);
    }

    @Override
    public AvancementDTO createAvancement(AvancementDTO avancementDTO) {
        Avancement avancement = avancementMapper.toEntity(avancementDTO);
        avancement = avancementRepository.save(avancement);
        return avancementMapper.toDTO(avancement);
    }

    @Override
    public AvancementDTO updateAvancement(Long id, AvancementDTO avancementDTO) {
        Avancement avancement = avancementMapper.toEntity(avancementDTO);
        avancement.setId(id);
        avancement = avancementRepository.save(avancement);
        return avancementMapper.toDTO(avancement);
    }

    @Override
    public void deleteAvancement(Long id) {
        avancementRepository.deleteById(id);
    }

    @Override
    public int getAvancementPercentage(Long participantId, Long formationId) {
        int sessionsAttended = avancementRepository.countSessionsAttendedByParticipant(participantId, formationId);
        int totalSessions = avancementRepository.countSessionsInFormation(formationId);

        if (totalSessions == 0) {
            return 0;
        }

        return (sessionsAttended * 100) / totalSessions;
    }

}
