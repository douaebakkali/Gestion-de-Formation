
package com.example.demo.Service;

import com.example.demo.dto.AvancementDTO;

import java.util.List;

public interface AvancementService {

    List<AvancementDTO> getAllAvancements();

    AvancementDTO getAvancementById(Long id);

    AvancementDTO getAvancement(Long participantId, Long formationId);

    AvancementDTO createAvancement(AvancementDTO avancementDTO);

    AvancementDTO updateAvancement(Long id, AvancementDTO avancementDTO);

    void deleteAvancement(Long id);

    int getAvancementPercentage(Long participantId, Long formationId);
}
