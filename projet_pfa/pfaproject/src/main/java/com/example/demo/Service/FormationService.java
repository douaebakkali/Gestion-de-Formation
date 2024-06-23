package com.example.demo.Service;

import com.example.demo.dto.FormationDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FormationService {
    List<FormationDTO> getAllFormations();
    Optional<FormationDTO> getFormationById(Long id);
    FormationDTO saveFormation(FormationDTO formation);
    void deleteFormationById(Long id);
    FormationDTO updateFormation(Long id, FormationDTO formation);
    List<FormationDTO> findFormationsByTitreContaining(String titre);
    List<FormationDTO> findFormationsByDomaineId(Long domaineId);

    List<FormationDTO> findFormationsByDomaine(String libelle);

    List<FormationDTO> findFormationsByDateDebutAfter(LocalDate date);
    List<FormationDTO> findFormationsByDateFinBefore(LocalDate date);
    List<FormationDTO> findFormationsByDateDebutBetween(LocalDate startDate, LocalDate endDate);
    List<FormationDTO> findFormationsByTitreContainingAndDateDebutBetween(String titre, LocalDate startDate, LocalDate endDate);

    Optional<FormationDTO> getFormationByTitle(String titre);

    List<FormationDTO> getFormationByDomaineLibelle(String libelle);
}

