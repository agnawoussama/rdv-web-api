package com.example.rdv_web_api.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rendezvous")
@JsonIdentityInfo(
    scope = RendezVous.class,
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
@Getter
@Setter
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;
    private String description;

    @Transient
    private Patient patient; 

    @Transient
    private Dentiste dentiste; 

    @Transient
    private Soin soin; 

    private Long patientId; 
    private Long dentisteId; 
    private Long soinId; 
}