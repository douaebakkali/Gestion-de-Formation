package com.example.demo.Service;

import com.example.demo.dto.DomaineDTO;

import java.util.List;
import java.util.Optional;

public interface DomaineService {

        List<DomaineDTO> getAllDomaines();
        Optional<DomaineDTO> getDomaineById(Long id);
        DomaineDTO saveDomaine(DomaineDTO domaineDTO);

        DomaineDTO updateDomaine(Long id, DomaineDTO domaineDTO);

        void deleteDomaineById(Long id);
}


