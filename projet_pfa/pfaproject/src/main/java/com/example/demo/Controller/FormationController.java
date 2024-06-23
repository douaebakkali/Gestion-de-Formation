package com.example.demo.Controller;
import com.example.demo.Service.FormationService;
import com.example.demo.dto.FormationDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/formations")
public class FormationController {

    private final FormationService formationService;

    @PostMapping
    public ResponseEntity<FormationDTO> createFormation(@RequestBody FormationDTO formationDTO) {
        FormationDTO savedFormation = formationService.saveFormation(formationDTO);
        return new ResponseEntity<>(savedFormation, HttpStatus.CREATED);
    }

  /*  @GetMapping
    public ResponseEntity<List<FormationDTO>> getAllFormations() {
        List<FormationDTO> formations = formationService.getAllFormations();
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }*/

    @GetMapping("/domaine/{libelle}")
    public ResponseEntity<List<FormationDTO>> getFormationByDomaineLibelle(@PathVariable String libelle) {
        List<FormationDTO> formations = formationService.getFormationByDomaineLibelle(libelle);
        if (formations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }

    @GetMapping("/{titre}")
    public ResponseEntity<FormationDTO> getFormationByTitle(@PathVariable String titre){
        Optional<FormationDTO> formationDTO = formationService.getFormationByTitle(titre);
        return formationDTO.map(formation -> new ResponseEntity<>(formation, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormationDTO> getFormationById(@PathVariable Long id) {
        Optional<FormationDTO> formationDTO = formationService.getFormationById(id);
        return formationDTO.map(formation -> new ResponseEntity<>(formation, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormationDTO> updateFormation(@PathVariable Long id, @RequestBody FormationDTO formationDTO) {
        FormationDTO updatedFormation = formationService.updateFormation(id, formationDTO);
        return new ResponseEntity<>(updatedFormation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable Long id) {
        formationService.deleteFormationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
