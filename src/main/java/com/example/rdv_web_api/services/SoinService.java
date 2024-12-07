package com.example.rdv_web_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.rdv_web_api.entities.Soin;
import com.example.rdv_web_api.repositories.SoinRepository;

@Service
public class SoinService {
    private final SoinRepository soinRepository;

    public SoinService(SoinRepository soinRepository) {
        this.soinRepository = soinRepository;
    }

    public List<Soin> obtenirTousLesSoins() {
        return soinRepository.findAll();
    }
}
