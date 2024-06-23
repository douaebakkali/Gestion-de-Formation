package com.example.demo.Repository;

import com.example.demo.entities.Domaine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomaineRepository extends JpaRepository<Domaine,Long> {
}
