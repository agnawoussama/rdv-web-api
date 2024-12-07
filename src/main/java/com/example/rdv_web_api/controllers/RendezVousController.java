package com.example.rdv_web_api.controllers;

import java.util.List;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.rdv_web_api.entities.RendezVous;
import com.example.rdv_web_api.services.RendezVousService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/rendezvous")
public class RendezVousController {
    private final RendezVousService rendezVousService;

    @Value("${rabbitmq.exchange.notifications}")
    private String exchange; 

    @Value("${rabbitmq.routing-key.notifications}")
    private String routingKey; 
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public RendezVousController(RabbitTemplate rabbitTemplate, RendezVousService rendezVousService) {
        this.rabbitTemplate = rabbitTemplate;
        this.rendezVousService = rendezVousService;
    }

    // Ajouter un rendezvous
    @PostMapping
    public RendezVous ajoutRendezVous(@RequestBody RendezVous rendezVous){

        String message = "Nouveau Rendez Vous Crée: " + rendezVous.getDescription();

        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return rendezVousService.ajouterRendezVous(rendezVous);
    }

    // Cherche tous les rendez vous
    @GetMapping
    public ResponseEntity<List<RendezVous>> obtenirTousLesRendezVous(){
        return new ResponseEntity<>(rendezVousService.obtenirTousLesRendezVous(),HttpStatus.OK);
    }
    
    // Chercher un rendezvous
    @GetMapping("/{id}")
    public ResponseEntity<RendezVous> obtenirRendezVous(@PathVariable("id") Long id) {
        return rendezVousService.obtenirRendezVous(id)
            .map(rendezvous -> ResponseEntity.ok(rendezvous)) 
            .orElse(ResponseEntity.notFound().build()); 
    }

    

    // Modifier un rendezvous
    @PutMapping()
    public RendezVous majRendezVous(@RequestBody RendezVous rendezVous) {    
        return rendezVousService.majRendezVous(rendezVous);
    }

    //Supprimer un rendezvous
    @DeleteMapping("/{id}")
    public void supprimerRendezVous(@PathVariable("id") Long id) {
        rendezVousService.supprimerRendezVous(id);
    }
}
