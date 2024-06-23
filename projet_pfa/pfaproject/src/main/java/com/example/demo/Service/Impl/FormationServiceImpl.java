package com.example.demo.Service.Impl;

import com.example.demo.Mapper.FormationMapper;
import com.example.demo.Repository.DomaineRepository;
import com.example.demo.Repository.FormationRepository;
import com.example.demo.Service.FormationService;
import com.example.demo.dto.FormationDTO;
import com.example.demo.entities.Domaine;
import com.example.demo.entities.Formation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FormationServiceImpl implements FormationService {

    @Autowired
    private FormationRepository formationRepository;
    @Autowired
    private DomaineRepository domaineRepository;

    @Autowired
    private FormationMapper formationMapper;

    @Override
    public List<FormationDTO> getAllFormations() {
        return formationRepository.findAll().stream()
                .map(formationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FormationDTO> getFormationById(Long id) {
        return formationRepository.findById(id)
                .map(formationMapper::toDTO);
    }

    @Override
    public FormationDTO saveFormation(FormationDTO formationDTO) {
        Formation formation = formationMapper.toEntity(formationDTO);
        formation = formationRepository.save(formation);
        return formationMapper.toDTO(formation);
    }

    @Override
    public void deleteFormationById(Long id) {
        formationRepository.deleteById(id);
    }

    @Override
    public FormationDTO updateFormation(Long id, FormationDTO formationDTO) {
        Formation formation = formationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Formation not found"));

        formation.setTitre(formationDTO.getTitre());
        formation.setDomaine(formationMapper.toEntity(formationDTO).getDomaine());
        formation.setDescriptionFormation(formationDTO.getDescriptionFormation());
        formation.setDateDebut(formationDTO.getDateDebut());
        formation.setDateFin(formationDTO.getDateFin());
        formation = formationRepository.save(formation);

        return formationMapper.toDTO(formation);
    }

    @Override
    public List<FormationDTO> findFormationsByTitreContaining(String titre) {
        return formationRepository.findByTitreContaining(titre).stream()
                .map(formationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FormationDTO> findFormationsByDomaineId(Long domaineId) {
        Domaine domaine = domaineRepository.findById(domaineId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Domaine ID: " + domaineId));
        return formationRepository.findByDomaine(domaine).stream()
                .map(formationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FormationDTO> findFormationsByDomaine(String libelle) {
        return formationRepository.findByDomaineLibelle(libelle).stream()
                .map(formationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FormationDTO> findFormationsByDateDebutAfter(LocalDate date) {
        return formationRepository.findByDateDebutAfter(date).stream()
                .map(formationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FormationDTO> findFormationsByDateFinBefore(LocalDate date) {
        return formationRepository.findByDateFinBefore(date).stream()
                .map(formationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FormationDTO> findFormationsByDateDebutBetween(LocalDate startDate, LocalDate endDate) {
        return formationRepository.findByDateDebutBetween(startDate, endDate).stream()
                .map(formationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FormationDTO> findFormationsByTitreContainingAndDateDebutBetween(String titre, LocalDate startDate, LocalDate endDate) {
        return formationRepository.findByTitreContainingAndDateDebutBetween(titre, startDate, endDate).stream()
                .map(formationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FormationDTO> getFormationByTitle(String titre) {
        return formationRepository.findBytitre(titre)
                .map(formationMapper::toDTO);
    }

    @Override
        public List<FormationDTO> getFormationByDomaineLibelle(String libelle) {
            List<Formation> formations = formationRepository.findByDomaineLibelle(libelle);
            // Convertir les entités Formation en DTO
            return formations.stream()
                    .map(formationMapper::toDTO)
                    .collect(Collectors.toList());
        }

}
