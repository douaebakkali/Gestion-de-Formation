package com.example.demo.Service;

import com.example.demo.dto.InscriptionDTO;

import java.util.List;

public interface InscriptionService {
    List<InscriptionDTO> getInscriptionsByParticipantId(Long participantId);
    List<InscriptionDTO> getInscriptionsByFormationId(Long formationId);
    InscriptionDTO getInscriptionById(Long id);
    InscriptionDTO saveInscription(InscriptionDTO inscriptionDTO);
    InscriptionDTO updateInscriptionAttendance(Long id, boolean attended);
    void deleteInscriptionById(Long id);
}
