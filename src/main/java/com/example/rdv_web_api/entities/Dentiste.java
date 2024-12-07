package com.example.rdv_web_api.entities;


import lombok.Data;

@Data
public class Dentiste {
    private Long id;
    private String nom;
    private String prenom;
    private String specialite;
}