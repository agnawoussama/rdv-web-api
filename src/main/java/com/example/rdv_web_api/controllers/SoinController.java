package com.example.rdv_web_api.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.rdv_web_api.entities.Soin;
import com.example.rdv_web_api.services.SoinService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/soins")

public class SoinController {
    private final SoinService soinService;
 
    public SoinController(SoinService soinService) {
        this.soinService = soinService;
    }

    // Cherche tous les soins
    @GetMapping
    public ResponseEntity<List<Soin>> obtenirTousLesSoins(){
        return new ResponseEntity<>(soinService.obtenirTousLesSoins(),HttpStatus.OK);
    }
}
