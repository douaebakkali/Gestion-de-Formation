package com.example.demo.Controller;

import com.example.demo.Service.AvancementService;
import com.example.demo.dto.AvancementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avancements")
public class AvancementController {

    @Autowired
    private AvancementService avancementService;

    @GetMapping
    public List<AvancementDTO> getAllAvancements() {
        return avancementService.getAllAvancements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvancementDTO> getAvancementById(@PathVariable Long id) {
        AvancementDTO avancement = avancementService.getAvancementById(id);
        return avancement != null ? ResponseEntity.ok(avancement) : ResponseEntity.notFound().build();
    }

    @GetMapping("/participant/{participantId}/formation/{formationId}")
    public ResponseEntity<AvancementDTO> getAvancement(@PathVariable Long participantId, @PathVariable Long formationId) {
        AvancementDTO avancementDTO = avancementService.getAvancement(participantId, formationId);
        return ResponseEntity.ok(avancementDTO);
    }

    @GetMapping("/participant/{participantId}/formation/{formationId}/percentage")
    public ResponseEntity<Integer> getAvancementPercentage(@PathVariable Long participantId, @PathVariable Long formationId) {
        int percentage = avancementService.getAvancementPercentage(participantId, formationId);
        return ResponseEntity.ok(percentage);
    }

    @PostMapping
    public ResponseEntity<AvancementDTO> createAvancement(@RequestBody AvancementDTO avancementDTO) {
        AvancementDTO createdAvancement = avancementService.createAvancement(avancementDTO);
        return ResponseEntity.ok(createdAvancement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvancementDTO> updateAvancement(@PathVariable Long id, @RequestBody AvancementDTO avancementDTO) {
        AvancementDTO updatedAvancement = avancementService.updateAvancement(id, avancementDTO);
        return ResponseEntity.ok(updatedAvancement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvancement(@PathVariable Long id) {
        avancementService.deleteAvancement(id);
        return ResponseEntity.noContent().build();
    }
}
