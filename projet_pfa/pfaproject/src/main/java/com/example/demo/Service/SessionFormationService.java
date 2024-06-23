package com.example.demo.Service;

import com.example.demo.dto.SessionFormationDTO;

import java.util.List;

public interface SessionFormationService {
    List<SessionFormationDTO> getAllSessionFormations();
    List<SessionFormationDTO> getSessionFormationsByFormationId(Long formationId);
    List<SessionFormationDTO> getSessionFormationsByFormateurId(Long formateurId);
    SessionFormationDTO getSessionFormationById(Long id);
    SessionFormationDTO saveSessionFormation(SessionFormationDTO sessionFormationDTO);
    SessionFormationDTO updateSessionFormation(Long id, SessionFormationDTO sessionFormationDTO);
    void deleteSessionFormationById(Long id);
}
