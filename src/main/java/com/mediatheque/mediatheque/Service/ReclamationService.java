package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.ReclamationDto;
import com.mediatheque.mediatheque.Entity.Emprunt;
import com.mediatheque.mediatheque.Entity.Reclamation;
import com.mediatheque.mediatheque.Entity.Employe;
import com.mediatheque.mediatheque.Repository.EmpruntRepository;
import com.mediatheque.mediatheque.Repository.ReclamationRepository;
import com.mediatheque.mediatheque.Repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;

    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private EmployeRepository employeRepository;

    public ReclamationDto createReclamation(ReclamationDto reclamationDto) {
        // Récupérer l'emprunt concerné
        Emprunt emprunt = empruntRepository.findById(reclamationDto.getEmpruntId())
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé"));

        // Récupérer l'employé qui envoie la réclamation
        Employe employe = employeRepository.findById(reclamationDto.getEmployeId())
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));

        // Créer une nouvelle réclamation
        Reclamation reclamation = new Reclamation();
        reclamation.setMessage(reclamationDto.getMessage());
        reclamation.setDateReclamation(new Date());
        reclamation.setEmprunt(emprunt);
        reclamation.setEmploye(employe);

        // Sauvegarder la réclamation
        Reclamation savedReclamation = reclamationRepository.save(reclamation);

        // Convertir en DTO pour la réponse
        return convertToDto(savedReclamation);
    }

    private ReclamationDto convertToDto(Reclamation reclamation) {
        ReclamationDto dto = new ReclamationDto();
        dto.setReclamationId(reclamation.getReclamationId());
        dto.setMessage(reclamation.getMessage());
        dto.setDateReclamation(reclamation.getDateReclamation());
        dto.setEmpruntId(reclamation.getEmprunt().getEmprunt_id());
        dto.setEmployeId(reclamation.getEmploye().getEmploye_id()); // Supposons que Employe a un ID
        return dto;
    }
    public List<ReclamationDto> getReclamationsByLecteurId(Long lecteurId) {
        List<Reclamation> reclamations = reclamationRepository.findByLecteurId(lecteurId);
        return reclamations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}