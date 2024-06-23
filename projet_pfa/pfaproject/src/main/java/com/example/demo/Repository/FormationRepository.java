package com.example.demo.Repository;

import com.example.demo.entities.Domaine;
import com.example.demo.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface FormationRepository extends JpaRepository<Formation,Long> {
    List<Formation> findByTitreContaining(String titre);
    List<Formation> findByDomaine(Domaine domaine);
    List<Formation> findByDomaineLibelle(String libelle);
    List<Formation> findByDateDebutAfter(LocalDate date);
    List<Formation> findByDateFinBefore(LocalDate date);
    List<Formation> findByDateDebutBetween(LocalDate startDate, LocalDate endDate);
    List<Formation> findByTitreContainingAndDateDebutBetween(String titre, LocalDate startDate, LocalDate endDate);


   // @Modifying
  //  @Query("DELETE FROM Formation f WHERE f.id = :id")
    void deleteById(Long id);

     Optional<Formation> findBytitre(String titre);

   /* @Modifying
    @Transactional
    @Query("UPDATE Formation f SET f.titre = :titre, f.domaine = :domaine, f.descriptionFormation = :description, f.dateDebut = :dateDebut, f.dateFin = :dateFin WHERE f.id = :id")
    void updateFormation(Long id, String titre, Domaine domaine, String description, LocalDate dateDebut, LocalDate dateFin);
    */
}
