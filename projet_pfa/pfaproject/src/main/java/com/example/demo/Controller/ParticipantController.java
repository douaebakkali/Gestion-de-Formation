package com.example.demo.Controller;

import com.example.demo.Service.ParticipantService;
import com.example.demo.dto.ParticipantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

   /* @GetMapping
    public List<ParticipantDTO> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerParticipant(@RequestBody ParticipantDTO participantDTO) {
        String password = participantDTO.getPassword(); // Récupérer le mot de passe du DTO
        participantService.registerParticipant(participantDTO, password);
        return ResponseEntity.ok().build();
    } */

   @GetMapping
    public ResponseEntity<List<ParticipantDTO>> getAllParticipants() {
        List<ParticipantDTO> fetchedUsers = participantService.getAllParticipants();
        return ResponseEntity.ok(fetchedUsers);
    }

    @GetMapping("/username")
    public ResponseEntity<ParticipantDTO> getParticipantByUsername(@RequestParam String username) {
        Optional<ParticipantDTO> participant = participantService.getParticipantByUsername(username);
        return participant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDTO> getParticipantById(@PathVariable Long id) {
        ParticipantDTO participant = participantService.getParticipantById(id);
        return participant != null ? ResponseEntity.ok(participant) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ParticipantDTO> createParticipant(@RequestBody ParticipantDTO participantDTO) {
        ParticipantDTO createdParticipant = participantService.createParticipant(participantDTO);
        return ResponseEntity.ok(createdParticipant);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ParticipantDTO> updateParticipant(@PathVariable Long id, @RequestBody ParticipantDTO participantDTO) {
        ParticipantDTO updatedParticipant = participantService.updateParticipant(id, participantDTO);
        return ResponseEntity.ok(updatedParticipant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email")
    public ResponseEntity<ParticipantDTO> getParticipantByEmail(@RequestParam String email) {
        Optional<ParticipantDTO> participant = participantService.getParticipantByEmail(email);
        return participant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<Void> updateEmail(@PathVariable Long id, @RequestParam String email) {
        participantService.updateEmail(id, email);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> checkForParticipantLogin(@RequestParam String email, @RequestParam String password) {
        boolean isValid = participantService.checkForParticipantLogin(email, password);
        return ResponseEntity.ok(isValid);
    }
}
