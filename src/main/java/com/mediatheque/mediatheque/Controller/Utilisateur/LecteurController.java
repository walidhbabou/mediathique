package com.mediatheque.mediatheque.Controller.Utilisateur;

import com.mediatheque.mediatheque.Dto.LecteurDto;
import com.mediatheque.mediatheque.Entity.Lecteur;
import com.mediatheque.mediatheque.Service.Utilisateur.LecteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Mediatheque/lecteur")
public class LecteurController {

    private final LecteurService lecteurService;

    @Autowired
    public LecteurController(LecteurService lecteurService) {
        this.lecteurService = lecteurService;
    }
    @GetMapping("/count")
    public ResponseEntity<Long> countLecteurs() {
        Long count = lecteurService.countLecteurs();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/allLecteurs")
    public List<LecteurDto> getAllLecteurs() {
        List<Lecteur> lecteurs = lecteurService.getAllLecteurs();
        return lecteurs.stream()
                .map(lecteur -> new LecteurDto(lecteur.getLecteurId(),lecteur.getUser()))
                .collect(Collectors.toList());
    }
    // Ajouter un point d'accès pour récupérer un lecteur par ID
    @GetMapping("/lecteur/{id}")
    public LecteurDto getLecteurById(@PathVariable Long id) {
        Lecteur lecteur = lecteurService.getLecteurById(id);
        return new LecteurDto(lecteur.getLecteurId(), lecteur.getUser());
    }
    // Nouvel endpoint pour récupérer un lecteur par l'ID de l'utilisateur
    @GetMapping("/byUserId/{userId}")
    public LecteurDto getLecteurByUserId(@PathVariable Long userId) {
        Lecteur lecteur = lecteurService.getLecteurByUserId(userId);
        return new LecteurDto(lecteur.getLecteurId(), lecteur.getUser());
    }
    @PutMapping("/update/{userId}")
    public ResponseEntity<LecteurDto> updateLecteur(@PathVariable Long userId, @RequestBody LecteurDto lecteurDto) {
        Lecteur updatedLecteur = lecteurService.updateLecteur(userId, lecteurDto);
        return ResponseEntity.ok(new LecteurDto(updatedLecteur.getLecteurId(), updatedLecteur.getUser()));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLecteur(@PathVariable Long id) {
        try {
            lecteurService.deleteLecteur(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur lors de la suppression du lecteur");
        }
    }
}
