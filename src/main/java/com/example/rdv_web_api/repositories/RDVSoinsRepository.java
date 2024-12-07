package com.example.rdv_web_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rdv_web_api.entities.RDVSoins;

public interface RDVSoinsRepository extends JpaRepository<RDVSoins,Long> {
    
}
