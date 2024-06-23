package com.example.demo.Repository;

import com.example.demo.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscription,Long> {
    List<Inscription> findByParticipantId(Long participantId);
    List<Inscription> findBySessionFormationFormationId(Long formationId);
}
