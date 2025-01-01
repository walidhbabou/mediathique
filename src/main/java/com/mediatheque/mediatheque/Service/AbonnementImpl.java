package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.AbonnementDto;
import com.mediatheque.mediatheque.Entity.Abonnement;
import com.mediatheque.mediatheque.Entity.Lecteur;
import com.mediatheque.mediatheque.Repository.AbonnementRepository;
import com.mediatheque.mediatheque.Repository.LecteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (abonnementDto.getLecteurId() == null) {
            throw new IllegalArgumentException("Lecteur ID must not be null");
        }

        // Retrieve the lecteur by ID
        Lecteur lecteur = lecteurRepository.findById(abonnementDto.getLecteurId())
                .orElseThrow(() -> new RuntimeException("Lecteur not found with id: " + abonnementDto.getLecteurId()));

        // Map the DTO to the entity
        Abonnement abonnement = new Abonnement();
        abonnement.setDate_expiration(abonnementDto.getDate_expiration());
        abonnement.setDate_inscription(abonnementDto.getDate_inscription());
        abonnement.setSolde(abonnementDto.getSolde());
        abonnement.setLecteur(lecteur); // Associate the existing lecteur

        // Save the abonnement
        Abonnement savedAbonnement = abonnementRepository.save(abonnement);

        // Map the saved entity back to DTO
        AbonnementDto savedAbonnementDto = new AbonnementDto();
        savedAbonnementDto.setAbonnementId(savedAbonnement.getAbonnementId());
        savedAbonnementDto.setDate_expiration(savedAbonnement.getDate_expiration());
        savedAbonnementDto.setDate_inscription(savedAbonnement.getDate_inscription());
        savedAbonnementDto.setSolde(savedAbonnement.getSolde());
        savedAbonnementDto.setLecteurId(savedAbonnement.getLecteur().getLecteurId());

        return savedAbonnementDto;
    }

    @Override
    public AbonnementDto updateAbonnement(Long id, AbonnementDto abonnementDto) {
        Abonnement abonnement = abonnementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Abonnement not found"));

        abonnement.setDate_inscription(abonnementDto.getDate_inscription());
        abonnement.setDate_expiration(abonnementDto.getDate_expiration());
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

    private AbonnementDto mapToDto(Abonnement abonnement) {
        return AbonnementDto.builder()
                .abonnementId(abonnement.getAbonnementId())
                .date_inscription(abonnement.getDate_inscription())
                .date_expiration(abonnement.getDate_expiration())
                .solde(abonnement.getSolde())
                .lecteurId(abonnement.getLecteur().getLecteurId())
                .build();
    }
}
