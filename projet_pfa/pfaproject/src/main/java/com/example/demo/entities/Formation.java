package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Formation")
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titre", nullable = false)
    private String titre;

    @ManyToOne(fetch = FetchType.LAZY) // les formations ne seront chargées en mémoire que lorsque vous y accéderez explicitement
    @JoinColumn(name = "domaine_id")
    private Domaine domaine;

    @Column(name = "description_formation")
    private String descriptionFormation;

    @Column(name = "dateDebut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "dateFin", nullable = false)
    private LocalDate dateFin;

    @OneToMany(mappedBy = "formation")
    private Collection<SessionFormation> sessions= new ArrayList<>();

    @OneToMany(mappedBy = "formation")
    private Collection<Avancement> avancements=new ArrayList<>();


}

