package com.example.demo.Controller;

import com.example.demo.Service.DomaineService;
import com.example.demo.dto.DomaineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/domaines")
public class DomaineController {

    @Autowired
    private DomaineService domaineService;

    @GetMapping
    public List<DomaineDTO> getAllDomaines() {
        return domaineService.getAllDomaines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DomaineDTO> getDomaineById(@PathVariable Long id) {
        Optional<DomaineDTO> domaine = domaineService.getDomaineById(id);
        return domaine.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DomaineDTO> saveDomaine(@RequestBody DomaineDTO domaineDTO) {
        DomaineDTO savedDomaine = domaineService.saveDomaine(domaineDTO);
        return ResponseEntity.ok(savedDomaine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DomaineDTO> updateDomaine(@PathVariable Long id, @RequestBody DomaineDTO domaineDTO) {
        DomaineDTO updatedDomaine = domaineService.updateDomaine(id, domaineDTO);
        return ResponseEntity.ok(updatedDomaine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDomaineById(@PathVariable Long id) {
        domaineService.deleteDomaineById(id);
        return ResponseEntity.noContent().build();
    }
}
