package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Avancement {

    @Id
    private Long id;;

    @ManyToOne
    @JoinColumn(name = "id_participant")
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "id_formation")
    private Formation formation;

}
