package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.MicroFilmDto;
import java.util.List;

public interface MicroFilmService {
    String addMicroFilm(MicroFilmDto microFilmDto);   // Ajouter un microfilm
    List<MicroFilmDto> getAllMicroFilms();             // Récupérer tous les microfilms
    MicroFilmDto getMicroFilmById(Long microId);       // Récupérer un microfilm par son ID
    String updateMicroFilm(MicroFilmDto microFilmDto); // Mettre à jour un microfilm
    String deleteMicroFilm(Long microId);              // Supprimer un microfilm
    List<MicroFilmDto> getMicroFilmsByDocumentId(Long documentId); // Rechercher un microfilm par document
}
