package com.example.rdv_web_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rdv_web_api.entities.Soin;

@Repository
public interface SoinRepository extends JpaRepository<Soin, Long> {
}
