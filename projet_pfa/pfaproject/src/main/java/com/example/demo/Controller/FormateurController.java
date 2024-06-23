package com.example.demo.Controller;

import com.example.demo.Service.FormateurService;
import com.example.demo.dto.FormateurDTO;
import com.example.demo.dto.ParticipantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/formateurs")
public class FormateurController {

    @Autowired
    private FormateurService formateurService;
/*
    @PostMapping("/register")
    public ResponseEntity<Void> registerFormateur(@RequestBody FormateurDTO formateurDTO) {
        String password = formateurDTO.getPassword(); // Récupérer le mot de passe du DTO
        formateurService.registerFormateur(formateurDTO, password);
        return ResponseEntity.ok().build();
    }*/

    @GetMapping
    public List<FormateurDTO> getAllFormateurs() {
        return formateurService.getAllFormateurs();
    }

   /* @GetMapping("/{id}")
    public ResponseEntity<FormateurDTO> getFormateurById(@PathVariable Long id) {
        FormateurDTO formateur = formateurService.getFormateurById(id);
        return formateur != null ? ResponseEntity.ok(formateur) : ResponseEntity.notFound().build();
    }*/

    @PostMapping
    public ResponseEntity<FormateurDTO> createFormateur(@RequestBody FormateurDTO formateurDTO) {
        FormateurDTO createdFormateur = formateurService.createFormateur(formateurDTO);
        return ResponseEntity.ok(createdFormateur);
    }


    @GetMapping("/{username}")
    public ResponseEntity<FormateurDTO> getFormateurByUsername(@RequestParam String username) {
        Optional<FormateurDTO> formateur = formateurService.getFormateurByUsername(username);
        return formateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormateurDTO> updateFormateur(@PathVariable Long id, @RequestBody FormateurDTO formateurDTO) {
        FormateurDTO updatedFormateur = formateurService.updateFormateur(id, formateurDTO);
        return ResponseEntity.ok(updatedFormateur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormateur(@PathVariable Long id) {
        formateurService.deleteFormateur(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email")
    public ResponseEntity<FormateurDTO> getFormateurByEmail(@RequestParam String email) {
        Optional<FormateurDTO> formateur = formateurService.getFormateurByEmail(email);
        return formateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/disponibilite")
    public List<FormateurDTO> getFormateurByDisponibilite(@RequestParam("start") String start, @RequestParam("end") String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return formateurService.getFormateurByDisponibilite(startDate, endDate);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> checkForFormateurLogin(@RequestParam String email, @RequestParam String password) {
        boolean isValid = formateurService.checkForFormateurLogin(email, password);
        return ResponseEntity.ok(isValid);
    }
}
