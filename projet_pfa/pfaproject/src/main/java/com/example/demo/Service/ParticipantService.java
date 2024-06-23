package com.example.demo.Service;

import com.example.demo.dto.ParticipantDTO;

import java.util.List;
import java.util.Optional;

public interface ParticipantService {

    List<ParticipantDTO> getAllParticipants();
    ParticipantDTO getParticipantById(Long id);
    ParticipantDTO createParticipant(ParticipantDTO participantDTO);
    ParticipantDTO updateParticipant(Long id, ParticipantDTO participantDTO);
    void deleteParticipant(Long id);
    public void updateEmail(Long id,String mail);
    boolean checkForParticipantLogin(String mail, String password);
    Optional<ParticipantDTO> getParticipantByEmail(String email);
    Optional<ParticipantDTO> getParticipantByUsername(String username);


}
