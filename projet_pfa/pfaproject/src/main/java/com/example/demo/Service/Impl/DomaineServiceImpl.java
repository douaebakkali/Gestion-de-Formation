package com.example.demo.Service.Impl;

import com.example.demo.Mapper.DomaineMapper;
import com.example.demo.Repository.DomaineRepository;
import com.example.demo.Service.DomaineService;
import com.example.demo.dto.DomaineDTO;
import com.example.demo.entities.Domaine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class DomaineServiceImpl implements DomaineService {

    @Autowired
    private DomaineRepository domaineRepository;

    @Autowired
    private DomaineMapper domaineMapper;

    @Override
    public List<DomaineDTO> getAllDomaines() {
        return domaineRepository.findAll().stream()
                .map(domaineMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DomaineDTO> getDomaineById(Long id) {
        return domaineRepository.findById(id)
                .map(domaineMapper::toDTO);
    }


    @Override
    public DomaineDTO saveDomaine(DomaineDTO domaineDTO) {
        Domaine domaine = domaineRepository.save(domaineMapper.toEntity(domaineDTO));
        return domaineMapper.toDTO(domaine);
    }

    @Override
    public DomaineDTO updateDomaine(Long id, DomaineDTO domaineDTO) {
        Domaine existingDomaine = domaineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Domaine not found with id: " + id));

        existingDomaine.setLibelle(domaineDTO.getLibelle());

        Domaine updatedDomaine = domaineRepository.save(existingDomaine);
        return domaineMapper.toDTO(updatedDomaine);
    }

    @Override
    public void deleteDomaineById(Long id) {
        domaineRepository.deleteById(id);
    }
}
