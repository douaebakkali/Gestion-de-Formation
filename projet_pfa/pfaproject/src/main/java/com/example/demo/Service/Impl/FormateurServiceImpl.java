package com.example.demo.Service.Impl;

import com.example.demo.Mapper.FormateurMapper;
import com.example.demo.Repository.FormateurRepository;
import com.example.demo.Repository.ParticipantRepository;
import com.example.demo.Service.FormateurService;
import com.example.demo.dto.FormateurDTO;
import com.example.demo.entities.Formateur;
import com.example.demo.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormateurServiceImpl implements FormateurService {

    @Autowired
    private FormateurRepository formateurRepository;

    @Autowired
    private FormateurMapper formateurMapper;

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public List<FormateurDTO> getAllFormateurs() {
        return formateurRepository.findAll().stream()
                .map(formateurMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FormateurDTO getFormateurById(Long id) {
        return formateurRepository.findById(id)
                .map(formateurMapper::toDTO)
                .orElse(null);
    }

    @Override
    public FormateurDTO createFormateur(FormateurDTO formateurDTO) {
        Formateur formateur = formateurMapper.toEntity(formateurDTO);
        formateur = formateurRepository.save(formateur);
        return formateurMapper.toDTO(formateur);
    }

    @Override
    public FormateurDTO updateFormateur(Long id, FormateurDTO formateurDTO) {
        Formateur formateur = formateurMapper.toEntity(formateurDTO);
        formateur.setId(id);
        formateur = formateurRepository.save(formateur);
        return formateurMapper.toDTO(formateur);
    }

    @Override
    public void deleteFormateur(Long id) {
        formateurRepository.deleteById(id);
    }

    @Override
    public Optional<FormateurDTO> getFormateurByEmail(String email) {
        Optional<Formateur> formateurOp =formateurRepository.findByEmail(email);
        if (formateurOp.isPresent()){
            Formateur formateur=formateurOp.get();
            return Optional.of(formateurMapper.toDTO(formateur));
        }
        return Optional.empty();
    }

    @Override
    public List<FormateurDTO> getFormateurByDisponibilite(LocalDate DD, LocalDate DF) {
        List<Formateur> formateurs = formateurRepository.getFormateurByDateDebutDisponibiliteAndDateFinDisponibilite(DD, DF);
        List<FormateurDTO> dtos = new ArrayList<>();

        for (Formateur formateur : formateurs) {
            FormateurDTO dto = formateurMapper.toDTO(formateur);
            dtos.add(dto);
        }
        return dtos;
    }


    @Override
    public boolean checkForFormateurLogin(String mail, String password) {
        Formateur formateur = formateurRepository.findByEmail(mail).orElseThrow(()-> new UserNotFoundException("User with mail"+mail+"not found"));
        return formateur.getPassword().equals(password);
    }

    @Override
    public Optional<FormateurDTO> getFormateurByUsername(String username) {
        Optional<Formateur> formateurOp = formateurRepository.findByUsername(username);
        if (formateurOp.isPresent()) {
            Formateur formateur =formateurOp.get();
            return Optional.of(formateurMapper.toDTO(formateur));
        }
        return Optional.empty();
    }


}