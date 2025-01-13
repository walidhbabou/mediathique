package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Entity.EtatEmprunt;
import com.mediatheque.mediatheque.Entity.Emprunt;
import com.mediatheque.mediatheque.model.Status;
import java.util.List;

public interface EtatEmpruntService {
    EtatEmprunt creerEtatEmprunt(Emprunt emprunt);
    EtatEmprunt updateStatus(Long empruntId, Status nouveauStatus);
    List<EtatEmprunt> getDemandesEnAttente();
    List<EtatEmprunt> getEtatEmpruntsByUser(Long userId);
    EtatEmprunt getEtatEmpruntById(Long id);
    void deleteEtatEmprunt(Long id);
}