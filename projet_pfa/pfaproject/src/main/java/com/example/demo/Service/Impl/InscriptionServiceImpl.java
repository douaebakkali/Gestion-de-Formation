package com.example.demo.Service.Impl;

import com.example.demo.Mapper.InscriptionMapper;
import com.example.demo.Repository.InscriptionRepository;
import com.example.demo.Service.InscriptionService;
import com.example.demo.dto.InscriptionDTO;
import com.example.demo.entities.Inscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscriptionServiceImpl implements InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private InscriptionMapper inscriptionMapper;

    @Override
    public List<InscriptionDTO> getInscriptionsByParticipantId(Long participantId) {
        return inscriptionRepository.findByParticipantId(participantId).stream()
                .map(inscriptionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<InscriptionDTO> getInscriptionsByFormationId(Long formationId) {
        return inscriptionRepository.findBySessionFormationFormationId(formationId).stream()
                .map(inscriptionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InscriptionDTO getInscriptionById(Long id) {
        return inscriptionRepository.findById(id)
                .map(inscriptionMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Inscription not found with id: " + id));
    }

    @Override
    public InscriptionDTO saveInscription(InscriptionDTO inscriptionDTO) {
        Inscription inscription = inscriptionMapper.toEntity(inscriptionDTO);
        Inscription savedInscription = inscriptionRepository.save(inscription);
        return inscriptionMapper.toDTO(savedInscription);
    }

    @Override
    public InscriptionDTO updateInscriptionAttendance(Long id, boolean attended) {
        Inscription existingInscription = inscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inscription not found with id: " + id));
        existingInscription.setAttended(attended);

        Inscription updatedInscription = inscriptionRepository.save(existingInscription);
        return inscriptionMapper.toDTO(updatedInscription);
    }

    @Override
    public void deleteInscriptionById(Long id) {
        inscriptionRepository.deleteById(id);
    }

    /*// Placeholder methods to fetch entities by ID, replace with actual implementation
    private SessionFormation fetchSessionFormationById(Long id) {
        // Fetch sessionFormation by ID
        return new SessionFormation();
    }

    private Participant fetchParticipantById(Long id) {
        // Fetch participant by ID
        return new Participant();
    }*/
}
