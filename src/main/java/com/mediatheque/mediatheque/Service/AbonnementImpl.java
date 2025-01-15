package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.AbonnementDto;
import com.mediatheque.mediatheque.Entity.Abonnement;
import com.mediatheque.mediatheque.Entity.Lecteur;
import com.mediatheque.mediatheque.Repository.AbonnementRepository;
import com.mediatheque.mediatheque.Repository.LecteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AbonnementImpl implements AbonnementService {

    @Autowired
    private AbonnementRepository abonnementRepository;
    @Autowired
    private LecteurRepository lecteurRepository;

    @Override
    public AbonnementDto createAbonnement(AbonnementDto abonnementDto) {
        // Validate that lecteurId is not null
        if (abonnementDto.getLecteur_id() == null) {
            throw new IllegalArgumentException("Lecteur ID must not be null");
        }

        // Retrieve the lecteur by ID
        Lecteur lecteur = lecteurRepository.findById(abonnementDto.getLecteur_id())
                .orElseThrow(() -> new RuntimeException("Lecteur not found with id: " + abonnementDto.getLecteur_id()));

        // Map the DTO to the entity
        Abonnement abonnement = new Abonnement();
        abonnement.setDateexpiration(abonnementDto.getDateexpiration());
        abonnement.setDateinscription(abonnementDto.getDateinscription());
        abonnement.setSolde(abonnementDto.getSolde());
        abonnement.setLecteur(lecteur); // Associate the existing lecteur

        // Save the abonnement
        Abonnement savedAbonnement = abonnementRepository.save(abonnement);

        // Map the saved entity back to DTO
        AbonnementDto savedAbonnementDto = new AbonnementDto();
        savedAbonnementDto.setAbonnementId(savedAbonnement.getAbonnementId());
        savedAbonnementDto.setDateexpiration(savedAbonnement.getDateexpiration());
        savedAbonnementDto.setDateinscription(savedAbonnement.getDateinscription());
        savedAbonnementDto.setSolde(savedAbonnement.getSolde());
        savedAbonnementDto.setLecteur_id(savedAbonnement.getLecteur().getLecteurId());

        return savedAbonnementDto;
    }

    @Override
    public AbonnementDto updateAbonnement(Long id, AbonnementDto abonnementDto) {
        Abonnement abonnement = abonnementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Abonnement not found"));

        abonnement.setDateinscription(abonnementDto.getDateinscription());
        abonnement.setDateexpiration(abonnementDto.getDateexpiration());
        abonnement.setSolde(abonnementDto.getSolde());


        Abonnement updatedAbonnement = abonnementRepository.save(abonnement);
        return mapToDto(updatedAbonnement);
    }

    @Override
    public void deleteAbonnement(Long id) {
        abonnementRepository.deleteById(id);
    }

    @Override
    public AbonnementDto getAbonnementById(Long id) {
        Abonnement abonnement = abonnementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Abonnement not found"));
        return mapToDto(abonnement);
    }

    @Override
    public List<AbonnementDto> getAllAbonnements() {
        List<Abonnement> abonnements = abonnementRepository.findAll();
        return abonnements.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AbonnementDto> findAbonnementsExpiringBefore(Date date_expiration) {
        List<Abonnement> abonnements = abonnementRepository.findByDateexpirationBefore(date_expiration);
        return abonnements.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    public List<AbonnementDto> getAbonnementsByLecteurId(Long lecteur_id) {
        List<Abonnement> abonnements = abonnementRepository.findAllByLecteurLecteurId(lecteur_id);
        if (abonnements.isEmpty()) {
            throw new RuntimeException("No abonnements found for lecteur with id: " + lecteur_id);
        }
        return abonnements.stream()
                .map(this::mapToDto)
                .toList();
    }

    private AbonnementDto mapToDto(Abonnement abonnement) {
        return AbonnementDto.builder()
                .abonnementId(abonnement.getAbonnementId())
                .dateinscription(abonnement.getDateinscription())
                .dateexpiration(abonnement.getDateexpiration())
                .solde(abonnement.getSolde())
                .lecteur_id(abonnement.getLecteur().getLecteurId())
                .lecteurName(abonnement.getLecteur().getUser().getUsername())
                .lecteurlastName(abonnement.getLecteur().getUser().getLastname())
                .build();
    }
    public Long countAbonnements() {
        return abonnementRepository.count();
    }

}
