package com.mediatheque.mediatheque.Controller;

import com.mediatheque.mediatheque.Dto.ReclamationDto;
import com.mediatheque.mediatheque.Service.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reclamations")
public class ReclamationController {

    @Autowired
    private ReclamationService reclamationService;

    @PostMapping("/envoyer")
    public ResponseEntity<?> envoyerReclamation(@RequestBody ReclamationDto reclamationDto) {
        try {
            ReclamationDto savedReclamation = reclamationService.createReclamation(reclamationDto);
            return ResponseEntity.ok(savedReclamation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/lecteur/{lecteurId}")
    public ResponseEntity<List<ReclamationDto>> getReclamationsByLecteurId(@PathVariable Long lecteurId) {
        List<ReclamationDto> reclamations = reclamationService.getReclamationsByLecteurId(lecteurId);
        return ResponseEntity.ok(reclamations);
    }
}