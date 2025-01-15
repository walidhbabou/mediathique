package com.mediatheque.mediatheque.Controller.Utilisateur;

import com.mediatheque.mediatheque.Dto.LecteurDto;
import com.mediatheque.mediatheque.Entity.Employe;
import com.mediatheque.mediatheque.Entity.Lecteur;
import com.mediatheque.mediatheque.Service.Utilisateur.EmployeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {

    @Autowired
    private EmployeServiceImpl employeService;

    // Récupérer tous les employés
    @GetMapping
    public ResponseEntity<List<Employe>> getAllEmployes() {
        List<Employe> employes = employeService.getAllEmployes();
        return ResponseEntity.ok(employes);
    }

    // Récupérer un employé par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable Long id) {
        Employe employe = employeService.getEmployeById(id);
        if (employe != null) {
            return ResponseEntity.ok(employe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<Employe> getEmployeByUserId(@PathVariable Long userId) {
        Employe employe = employeService.getEmployerByUserId(userId);
        if (employe != null) {
            return ResponseEntity.ok(employe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
