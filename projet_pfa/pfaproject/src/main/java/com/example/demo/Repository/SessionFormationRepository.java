package com.example.demo.Repository;

import com.example.demo.entities.SessionFormation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionFormationRepository extends JpaRepository<SessionFormation,Long> {
    List<SessionFormation> findByFormationId(Long formationId);
    List<SessionFormation> findByFormateurId(Long formateurId);
}
