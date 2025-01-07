package com.mediatheque.mediatheque.Service.Utilisateur;

import com.mediatheque.mediatheque.Entity.Lecteur;
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
}