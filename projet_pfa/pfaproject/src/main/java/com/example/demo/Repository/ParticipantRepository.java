package com.example.demo.Repository;

import com.example.demo.entities.Participant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Participant p SET p.email = :newEmail WHERE p.id = :participantId")
    void updateEmail(Long participantId, String newEmail);

    Optional<Participant> findByEmail(String email);
    Optional<Participant> findByUsername(String username);
}
