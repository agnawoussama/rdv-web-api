package com.example.rdv_web_api.services;

import com.example.rdv_web_api.entities.Dentiste;
import com.example.rdv_web_api.entities.Patient;
import com.example.rdv_web_api.entities.RendezVous;
import com.example.rdv_web_api.entities.Soin;
import com.example.rdv_web_api.repositories.RendezVousRepository;
import com.example.rdv_web_api.repositories.SoinRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Service
public class RendezVousService {
    private final RendezVousRepository rendezVousRepository;
    private final RestTemplate restTemplate;
    private final SoinRepository soinRepository;

    public RendezVousService(RendezVousRepository rendezVousRepository, RestTemplate restTemplate, SoinRepository soinRepository) {
        this.rendezVousRepository = rendezVousRepository;
        this.restTemplate = restTemplate;
        this.soinRepository = soinRepository;
    }

    // Chercher les rendezvous avec kes patients & dentistes
    public List<RendezVous> obtenirTousLesRendezVous() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        rendezVousList.forEach(this::populatePatientAndDentiste);
        return rendezVousList;
    }

    // Chercher Patient & dentiste & soin pour chaque rendez vous
    public RendezVous populatePatientAndDentiste(RendezVous rendezVous) {
        Patient patient = null;
        if (rendezVous.getPatientId() != null && rendezVous.getPatientId() > 0) {
            patient = restTemplate.getForObject("http://localhost:4040/api/patients/" + rendezVous.getPatientId(), Patient.class);
        }
        Dentiste dentiste = restTemplate.getForObject("http://localhost:4040/api/dentistes/" + rendezVous.getDentisteId(), Dentiste.class);
        Soin soin = soinRepository.findById(rendezVous.getSoinId()).orElse(null);

        rendezVous.setPatient(patient);
        rendezVous.setDentiste(dentiste);
        rendezVous.setSoin(soin);
    
        return rendezVous;
    }

    // Ajouter Rendezvous
    public RendezVous ajouterRendezVous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    // Supprimer Rendezvous
    public void supprimerRendezVous(Long id) {
        rendezVousRepository.deleteById(id);
    }

    // Modifier Rendezvous
    public RendezVous majRendezVous(RendezVous rendezVous) {
        RendezVous nvRendezVous = rendezVousRepository.getReferenceById(rendezVous.getId());
        nvRendezVous.setDate(rendezVous.getDate());
        nvRendezVous.setDescription(rendezVous.getDescription());
        nvRendezVous.setPatientId(rendezVous.getPatientId());
        nvRendezVous.setDentisteId(rendezVous.getDentisteId());
        nvRendezVous.setSoinId(rendezVous.getSoinId());;
        return rendezVousRepository.save(nvRendezVous);
    }

    public Optional<RendezVous> obtenirRendezVous(Long id) {
        return rendezVousRepository.findById(id);
    }
}