package com.example.demo.Mapper;

import com.example.demo.dto.FormateurDTO;
import com.example.demo.entities.ERole;
import com.example.demo.entities.Formateur;
import org.springframework.stereotype.Component;


@Component
public class FormateurMapper {
    public FormateurDTO toDTO(Formateur formateur) {
        FormateurDTO dto = new FormateurDTO();
        dto.setId(formateur.getId());
        dto.setUsername(formateur.getUsername());
        dto.setPassword(formateur.getPassword());
        dto.setNom(formateur.getNom());
        dto.setPrenom(formateur.getPrenom());
        dto.setEmail(formateur.getEmail());
        dto.setRole(formateur.getRole().name());
        dto.setCompetences(formateur.getCompetences());
        dto.setExperiencesProfessionnelles(formateur.getExperiencesProfessionnelles());
        dto.setDateDebutDisponibilite(formateur.getDateDebutDisponibilite());
        dto.setHeureDebutDisponibilite(formateur.getHeureDebutDisponibilite());
        dto.setDateFinDisponibilite(formateur.getDateFinDisponibilite());
        dto.setHeureFinDisponibilite(formateur.getHeureFinDisponibilite());
        return dto;
    }

    public Formateur toEntity(FormateurDTO dto) {
        Formateur formateur = new Formateur();
        formateur.setUsername(dto.getUsername());
        formateur.setPassword(dto.getPassword());
        formateur.setNom(dto.getNom());
        formateur.setPrenom(dto.getPrenom());
        formateur.setEmail(dto.getEmail());
        formateur.setRole(ERole.valueOf(dto.getRole()));
        formateur.setCompetences(dto.getCompetences());
        formateur.setExperiencesProfessionnelles(dto.getExperiencesProfessionnelles());
        formateur.setDateDebutDisponibilite(dto.getDateDebutDisponibilite());
        formateur.setHeureDebutDisponibilite(dto.getHeureDebutDisponibilite());
        formateur.setDateFinDisponibilite(dto.getDateFinDisponibilite());
        formateur.setHeureFinDisponibilite(dto.getHeureFinDisponibilite());
        return formateur;
    }
}
