package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Entity.Emprunt;
import com.mediatheque.mediatheque.model.Status;
import com.mediatheque.mediatheque.Entity.EtatEmprunt;
import com.mediatheque.mediatheque.Repository.EtatEmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EtatEmpruntImpl implements EtatEmpruntService {

    @Autowired
    private EtatEmpruntRepository etatEmpruntRepository;

    public EtatEmpruntImpl(EtatEmpruntRepository etatEmpruntRepository) {
        this.etatEmpruntRepository = etatEmpruntRepository;
    }

    @Override
    public EtatEmprunt creerEtatEmprunt(Emprunt emprunt) {
        try {
            EtatEmprunt etatEmprunt = new EtatEmprunt();
            etatEmprunt.setEmprunt(emprunt);
            etatEmprunt.setStatus(Status.EN_ATTENTE);
            etatEmprunt.setDateModification(LocalDate.now());
            return etatEmpruntRepository.save(etatEmprunt);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de l'état d'emprunt: " + e.getMessage());
        }
    }

    @Override
    public EtatEmprunt updateStatus(Long empruntId, Status nouveauStatus) {
        try {
            EtatEmprunt etatEmprunt = etatEmpruntRepository.findByEmprunt_Id(empruntId)
                    .orElseThrow(() -> new RuntimeException("État d'emprunt non trouvé pour l'ID: " + empruntId));

            etatEmprunt.setStatus(nouveauStatus);
            etatEmprunt.setDateModification(LocalDate.now());
            return etatEmpruntRepository.save(etatEmprunt);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la mise à jour du statut: " + e.getMessage());
        }
    }

    @Override
    public List<EtatEmprunt> getDemandesEnAttente() {
        try {
            return etatEmpruntRepository.findByStatus(Status.EN_ATTENTE);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des demandes en attente: " + e.getMessage());
        }
    }

    @Override
    public List<EtatEmprunt> getEtatEmpruntsByUser(Long userId) {
        try {
            return etatEmpruntRepository.findByEmprunt_Lecteur_Id(userId);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des états d'emprunt pour l'utilisateur: " + e.getMessage());
        }
    }

    @Override
    public EtatEmprunt getEtatEmpruntById(Long id) {
        return etatEmpruntRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("État d'emprunt non trouvé pour l'ID: " + id));
    }

    @Override
    public void deleteEtatEmprunt(Long id) {
        try {
            etatEmpruntRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression de l'état d'emprunt: " + e.getMessage());
        }
    }
}