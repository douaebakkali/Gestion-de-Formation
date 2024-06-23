package com.example.demo.Service;

import com.example.demo.dto.FormateurDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FormateurService {
    List<FormateurDTO> getAllFormateurs();
    FormateurDTO getFormateurById(Long id);
    FormateurDTO createFormateur(FormateurDTO formateurDTO);
    FormateurDTO updateFormateur(Long id, FormateurDTO formateurDTO);
    void deleteFormateur(Long id);
    public Optional<FormateurDTO> getFormateurByEmail(String email);
    List<FormateurDTO> getFormateurByDisponibilite(LocalDate DD, LocalDate DF);
    public boolean checkForFormateurLogin(String mail, String password);

    Optional<FormateurDTO> getFormateurByUsername(String username);
}
