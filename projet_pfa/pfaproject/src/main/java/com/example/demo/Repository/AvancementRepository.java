package com.example.demo.Repository;

import com.example.demo.entities.Avancement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AvancementRepository extends JpaRepository<Avancement,Long> {
    @Query("SELECT a FROM Avancement a WHERE a.participant.id = :participantId AND a.formation.id = :formationId")
    Optional<Avancement> findByParticipantIdAndFormationId(@Param("participantId") Long participantId, @Param("formationId") Long formationId);

    @Query("SELECT COUNT(i) FROM Inscription i WHERE i.participant.id = :participantId AND i.sessionFormation.formation.id = :formationId AND i.attended = true")
    int countSessionsAttendedByParticipant(@Param("participantId") Long participantId, @Param("formationId") Long formationId);

    @Query("SELECT COUNT(s) FROM SessionFormation s WHERE s.formation.id = :formationId")
    int countSessionsInFormation(@Param("formationId") Long formationId);

}

