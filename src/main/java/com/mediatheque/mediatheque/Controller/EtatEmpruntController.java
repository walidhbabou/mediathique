package com.mediatheque.mediatheque.Controller;

import com.mediatheque.mediatheque.Entity.EtatEmprunt;
import com.mediatheque.mediatheque.Service.EtatEmpruntImpl;
import com.mediatheque.mediatheque.model.Status;
import com.mediatheque.mediatheque.Service.EtatEmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etat-emprunt")
@CrossOrigin(origins = "*")
public class EtatEmpruntController {

    @Autowired
    private EtatEmpruntImpl etatEmpruntService;

    public EtatEmpruntController(EtatEmpruntImpl etatEmpruntService) {

        this.etatEmpruntService=etatEmpruntService;
    }

    @GetMapping("/demandes-en-attente")
    public ResponseEntity<List<EtatEmprunt>> getDemandesEnAttente() {
        return ResponseEntity.ok(etatEmpruntService.getDemandesEnAttente());
    }

    @PutMapping("/update/{empruntId}")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long empruntId,
            @RequestParam Status nouveauStatus) {
        try {
            EtatEmprunt etatEmprunt = etatEmpruntService.updateStatus(empruntId, nouveauStatus);
            return ResponseEntity.ok(etatEmprunt);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EtatEmprunt>> getEtatEmpruntsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(etatEmpruntService.getEtatEmpruntsByUser(userId));
    }
}