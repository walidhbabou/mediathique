package com.mediatheque.mediatheque.Service.Utilisateur;

import com.mediatheque.mediatheque.Dto.LecteurDto;
import com.mediatheque.mediatheque.Entity.Lecteur;
import com.mediatheque.mediatheque.Entity.User;
import com.mediatheque.mediatheque.Repository.LecteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LecteurServiceImpl implements LecteurService {

    private final LecteurRepository lecteurRepository;

    @Autowired
    public LecteurServiceImpl(LecteurRepository lecteurRepository) {
        this.lecteurRepository = lecteurRepository;
    }

    @Override
    public List<Lecteur> getAllLecteurs() {
        return lecteurRepository.findAll();
    }

    @Override
    public Lecteur getLecteurById(Long id) {
        return lecteurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lecteur introuvable avec l'id : " + id));
    }

    @Override
    public Lecteur getLecteurByUserId(Long userId) {
        return lecteurRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Lecteur introuvable pour l'utilisateur avec l'id : " + userId));
    }

    @Override
    public Lecteur updateLecteur(Long userId, LecteurDto lecteurDto) {
        Lecteur lecteur = lecteurRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Lecteur introuvable pour l'utilisateur avec l'id : " + userId));

        User user = lecteur.getUser();
        user.setUsername(lecteurDto.getUser().getUsername());
        user.setEmail(lecteurDto.getUser().getEmail());
        user.setLastname(lecteurDto.getUser().getLastname());
        user.setLastname(lecteurDto.getUser().getLastname());

        return lecteurRepository.save(lecteur);
    }
    @Override
    public void deleteLecteur(Long id) {
        if (!lecteurRepository.existsById(id)) {
            throw new IllegalArgumentException("Lecteur introuvable avec l'id : " + id);
        }
        lecteurRepository.deleteById(id);
    }

    @Override
    public Long countLecteurs() {
        return lecteurRepository.count();
    }

}