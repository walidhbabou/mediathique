package com.mediatheque.mediatheque.Service.Utilisateur;

import com.mediatheque.mediatheque.Dto.LecteurDto;
import com.mediatheque.mediatheque.Entity.Lecteur;

import java.util.List;

public interface LecteurService {
    List<Lecteur> getAllLecteurs();
    Lecteur getLecteurById(Long id); // Nouvelle méthode

    // Nouvelle méthode pour récupérer un lecteur par l'ID de l'utilisateur
    Lecteur getLecteurByUserId(Long userId);

    Lecteur updateLecteur(Long userId, LecteurDto lecteurDto);

    void deleteLecteur(Long id);

    Long countLecteurs();
}
