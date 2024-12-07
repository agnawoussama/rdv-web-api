package com.example.rdv_web_api.entities;

import lombok.Data;

@Data
public class Patient {
    private Long id;
    private String nom;
    private String prenom;
    private int age;
    private String derniereVisite;
}