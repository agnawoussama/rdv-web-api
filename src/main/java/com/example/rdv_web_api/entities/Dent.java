package com.example.rdv_web_api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Dent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String position;
}