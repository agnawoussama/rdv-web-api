package com.example.rdv_web_api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RDVSoins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @ManyToOne
    @JoinColumn(name = "idRendezVous")
    private RendezVous rendezVous;

    @ManyToOne
    @JoinColumn(name = "idSoin")
    private Soin soin;

    @ManyToOne
    @JoinColumn(name = "idDent")
    private Dent dent;
}
