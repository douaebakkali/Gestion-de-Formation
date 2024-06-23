package com.example.demo.Service.Impl;


import com.example.demo.Mapper.SessionFormationMapper;
import com.example.demo.Repository.FormateurRepository;
import com.example.demo.Repository.FormationRepository;
import com.example.demo.Repository.InscriptionRepository;
import com.example.demo.Repository.SessionFormationRepository;
import com.example.demo.Service.SessionFormationService;
import com.example.demo.dto.SessionFormationDTO;
import com.example.demo.entities.Formateur;
import com.example.demo.entities.Formation;
import com.example.demo.entities.Inscription;
import com.example.demo.entities.SessionFormation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionFormationServiceImpl implements SessionFormationService {

    @Autowired
    private SessionFormationRepository sessionFormationRepository;

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private FormateurRepository formateurRepository;

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private SessionFormationMapper sessionFormationMapper;

    @Override
    public List<SessionFormationDTO> getAllSessionFormations() {
        return sessionFormationRepository.findAll().stream()
                .map(sessionFormationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SessionFormationDTO> getSessionFormationsByFormationId(Long formationId) {
        return sessionFormationRepository.findByFormationId(formationId).stream()
                .map(sessionFormationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SessionFormationDTO> getSessionFormationsByFormateurId(Long formateurId) {
        return sessionFormationRepository.findByFormateurId(formateurId).stream()
                .map(sessionFormationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SessionFormationDTO getSessionFormationById(Long id) {
        return sessionFormationRepository.findById(id)
                .map(sessionFormationMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("SessionFormation not found with id: " + id));
    }

    @Override
    public SessionFormationDTO saveSessionFormation(SessionFormationDTO sessionFormationDTO) {
        Formation formation = formationRepository.findById(sessionFormationDTO.getFormationId())
                .orElseThrow(() -> new IllegalArgumentException("Formation not found with id: " + sessionFormationDTO.getFormationId()));
        Formateur formateur = formateurRepository.findById(sessionFormationDTO.getFormateurId())
                .orElseThrow(() -> new IllegalArgumentException("Formateur not found with id: " + sessionFormationDTO.getFormateurId()));

        List<Inscription> inscriptions;
        if (sessionFormationDTO.getInscriptionIds() == null) {
            // Handle the null case appropriately, e.g., return an empty list
            inscriptions = new ArrayList<>();
        } else {
            inscriptions = inscriptionRepository.findAllById(sessionFormationDTO.getInscriptionIds());
        }

        SessionFormation sessionFormation = sessionFormationMapper.toEntity(sessionFormationDTO, formation, formateur, inscriptions);
        SessionFormation savedSessionFormation = sessionFormationRepository.save(sessionFormation);

        return sessionFormationMapper.toDTO(savedSessionFormation);
    }


    @Override
    public SessionFormationDTO updateSessionFormation(Long id, SessionFormationDTO sessionFormationDTO) {
        SessionFormation existingSessionFormation = sessionFormationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SessionFormation not found with id: " + id));

        Formation formation = formationRepository.findById(sessionFormationDTO.getFormationId())
                .orElseThrow(() -> new IllegalArgumentException("Formation not found with id: " + sessionFormationDTO.getFormationId()));
        Formateur formateur = formateurRepository.findById(sessionFormationDTO.getFormateurId())
                .orElseThrow(() -> new IllegalArgumentException("Formateur not found with id: " + sessionFormationDTO.getFormateurId()));
        List<Inscription> inscriptions = inscriptionRepository.findAllById(sessionFormationDTO.getInscriptionIds());

        existingSessionFormation.setFormation(formation);
        existingSessionFormation.setFormateur(formateur);
        existingSessionFormation.setDateSession(sessionFormationDTO.getDateSession());
        existingSessionFormation.setHeureDebut(sessionFormationDTO.getHeureDebut());
        existingSessionFormation.setHeureFin(sessionFormationDTO.getHeureFin());
        existingSessionFormation.setInscriptions(inscriptions);

        SessionFormation updatedSessionFormation = sessionFormationRepository.save(existingSessionFormation);
        return sessionFormationMapper.toDTO(updatedSessionFormation);
    }

    @Override
    public void deleteSessionFormationById(Long id) {
        sessionFormationRepository.deleteById(id);
    }
}
