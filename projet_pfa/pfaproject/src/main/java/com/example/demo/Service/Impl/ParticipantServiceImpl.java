package com.example.demo.Service.Impl;

import com.example.demo.Mapper.ParticipantMapper;
import com.example.demo.Repository.ParticipantRepository;
import com.example.demo.Service.ParticipantService;
import com.example.demo.dto.ParticipantDTO;
import com.example.demo.entities.Participant;
import com.example.demo.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private ParticipantMapper participantMapper;


    @Override
    public List<ParticipantDTO> getAllParticipants() {
        return participantRepository.findAll().stream()
                .map(participantMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipantDTO getParticipantById(Long id) {
        return participantRepository.findById(id)
                .map(participantMapper::toDTO)
                .orElse(null);
    }

    @Override
    public ParticipantDTO createParticipant(ParticipantDTO participantDTO) {
        Participant participant = participantMapper.toEntity(participantDTO);
        participant = participantRepository.save(participant);
        return participantMapper.toDTO(participant);
    }

    @Override
    public ParticipantDTO updateParticipant(Long id, ParticipantDTO participantDTO) {
        Participant participant = participantMapper.toEntity(participantDTO);
        participant.setId(id);
        participant = participantRepository.save(participant);
        return participantMapper.toDTO(participant);
    }

    @Override
    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }

    @Override
    public Optional<ParticipantDTO> getParticipantByEmail(String email) {
        Optional<Participant> participantOp = participantRepository.findByEmail(email);
        if (participantOp.isPresent()) {
            Participant participant=participantOp.get();
            return Optional.of(participantMapper.toDTO(participant));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ParticipantDTO> getParticipantByUsername(String username) {
        Optional<Participant> participantOp = participantRepository.findByUsername(username);
        if (participantOp.isPresent()) {
            Participant participant=participantOp.get();
            return Optional.of(participantMapper.toDTO(participant));
        }
        return Optional.empty();
    }


    @Override
    public void updateEmail(Long id,String mail) {
        participantRepository.updateEmail(id,mail);
    }


    @Override
    public boolean checkForParticipantLogin(String email, String password) {
       Participant participant=participantRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Participant with given mail:" +email+" not found"));
       return participant.getPassword().equals(password);
    }


}
