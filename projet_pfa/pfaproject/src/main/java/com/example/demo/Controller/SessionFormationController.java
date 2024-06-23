package com.example.demo.Controller;


import com.example.demo.Service.SessionFormationService;
import com.example.demo.dto.SessionFormationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessionFormations")
public class SessionFormationController {

    @Autowired
    private SessionFormationService sessionFormationService;

    @GetMapping
    public ResponseEntity<List<SessionFormationDTO>> getAllSessionFormations() {
        List<SessionFormationDTO> sessionFormations = sessionFormationService.getAllSessionFormations();
        return ResponseEntity.ok(sessionFormations);
    }

    @GetMapping("/formation/{formationId}")
    public ResponseEntity<List<SessionFormationDTO>> getSessionFormationsByFormationId(@PathVariable Long formationId) {
        List<SessionFormationDTO> sessionFormations = sessionFormationService.getSessionFormationsByFormationId(formationId);
        return ResponseEntity.ok(sessionFormations);
    }

    @GetMapping("/formateur/{formateurId}")
    public ResponseEntity<List<SessionFormationDTO>> getSessionFormationsByFormateurId(@PathVariable Long formateurId) {
        List<SessionFormationDTO> sessionFormations = sessionFormationService.getSessionFormationsByFormateurId(formateurId);
        return ResponseEntity.ok(sessionFormations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionFormationDTO> getSessionFormationById(@PathVariable Long id) {
        SessionFormationDTO sessionFormation = sessionFormationService.getSessionFormationById(id);
        return ResponseEntity.ok(sessionFormation);
    }

    @PostMapping
    public ResponseEntity<SessionFormationDTO> createSessionFormation(@RequestBody SessionFormationDTO sessionFormationDTO) {
        SessionFormationDTO createdSessionFormation = sessionFormationService.saveSessionFormation(sessionFormationDTO);
        return ResponseEntity.ok(createdSessionFormation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionFormationDTO> updateSessionFormation(@PathVariable Long id, @RequestBody SessionFormationDTO sessionFormationDTO) {
        SessionFormationDTO updatedSessionFormation = sessionFormationService.updateSessionFormation(id, sessionFormationDTO);
        return ResponseEntity.ok(updatedSessionFormation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSessionFormation(@PathVariable Long id) {
        sessionFormationService.deleteSessionFormationById(id);
        return ResponseEntity.noContent().build();
    }
}
