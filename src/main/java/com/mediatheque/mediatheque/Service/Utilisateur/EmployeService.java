package com.mediatheque.mediatheque.Service.Utilisateur;

import com.mediatheque.mediatheque.Entity.Employe;
import com.mediatheque.mediatheque.Entity.Lecteur;

import java.util.List;

public interface EmployeService {

    List<Employe> getAllEmployes();
    Employe getEmployeById(Long id);
    Employe getEmployerByUserId(Long userId);
}
