package com.example.demo.Controller;

import com.example.demo.Service.InscriptionService;
import com.example.demo.dto.InscriptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;

    @GetMapping("/participant/{participantId}")
    public ResponseEntity<List<InscriptionDTO>> getInscriptionsByParticipantId(@PathVariable Long participantId) {
        List<InscriptionDTO> inscriptions = inscriptionService.getInscriptionsByParticipantId(participantId);
        return ResponseEntity.ok(inscriptions);
    }

    @GetMapping("/formation/{formationId}")
    public ResponseEntity<List<InscriptionDTO>> getInscriptionsByFormationId(@PathVariable Long formationId) {
        List<InscriptionDTO> inscriptions = inscriptionService.getInscriptionsByFormationId(formationId);
        return ResponseEntity.ok(inscriptions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscriptionDTO> getInscriptionById(@PathVariable Long id) {
        InscriptionDTO inscription = inscriptionService.getInscriptionById(id);
        return ResponseEntity.ok(inscription);
    }

    @PostMapping
    public ResponseEntity<InscriptionDTO> createInscription(@RequestBody InscriptionDTO inscriptionDTO) {
        InscriptionDTO createdInscription = inscriptionService.saveInscription(inscriptionDTO);
        return ResponseEntity.ok(createdInscription);
    }

    @PutMapping("/{id}/attendance")
    public ResponseEntity<InscriptionDTO> updateInscriptionAttendance(@PathVariable Long id, @RequestBody boolean attended) {
        InscriptionDTO updatedInscription = inscriptionService.updateInscriptionAttendance(id, attended);
        return ResponseEntity.ok(updatedInscription);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInscription(@PathVariable Long id) {
        inscriptionService.deleteInscriptionById(id);
        return ResponseEntity.noContent().build();
    }
}
