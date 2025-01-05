package com.mediatheque.mediatheque.Controller.Utilisateur;

import com.mediatheque.mediatheque.Dto.LecteurDto;
import com.mediatheque.mediatheque.Entity.Lecteur;
import com.mediatheque.mediatheque.Service.Utilisateur.LecteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/allLecteurs")
    public List<LecteurDto> getAllLecteurs() {
        List<Lecteur> lecteurs = lecteurService.getAllLecteurs();
        return lecteurs.stream()
                .map(lecteur -> new LecteurDto(lecteur.getLecteurId(),lecteur.getUser()))
                .collect(Collectors.toList());
    }
}