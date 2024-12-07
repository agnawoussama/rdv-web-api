package com.example.rdv_web_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rdv_web_api.entities.RendezVous;

public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
    
}
