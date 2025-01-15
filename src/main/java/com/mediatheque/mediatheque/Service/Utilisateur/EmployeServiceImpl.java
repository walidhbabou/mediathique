package com.mediatheque.mediatheque.Service.Utilisateur;

import com.mediatheque.mediatheque.Entity.Employe;
import com.mediatheque.mediatheque.Entity.Lecteur;
import com.mediatheque.mediatheque.Repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeServiceImpl implements EmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public Employe getEmployeById(Long id) {
        return employeRepository.findById(id).orElse(null);
    }
    @Override
    public Employe getEmployerByUserId(Long userId) {
        return employeRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Employé introuvable pour l'utilisateur avec l'id : " + userId));
    }

}
