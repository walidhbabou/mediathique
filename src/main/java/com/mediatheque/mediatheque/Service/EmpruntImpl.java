package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.AbonnementDto;
import com.mediatheque.mediatheque.Dto.DocumentDto;
import com.mediatheque.mediatheque.Dto.EmpruntDto;
import com.mediatheque.mediatheque.Entity.Abonnement;
import com.mediatheque.mediatheque.Entity.Document;
import com.mediatheque.mediatheque.Entity.Emprunt;
import com.mediatheque.mediatheque.Repository.AbonnementRepository;
import com.mediatheque.mediatheque.Repository.DocumentRepository;
import com.mediatheque.mediatheque.Repository.EmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class EmpruntImpl implements EmpruntService{

    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public EmpruntDto createEmprunt(EmpruntDto empruntDto) {
        // Vérifier si l'abonnement et son ID sont présents
        if (empruntDto.getAbonnement() == null || empruntDto.getAbonnement().getAbonnementId() == null) {
            throw new IllegalArgumentException("Abonnement ID must not be null");
        }

        // Vérifier si le document et son ID sont présents
        if (empruntDto.getDocument() == null || empruntDto.getDocument().getDocument_id() == null) {
            throw new IllegalArgumentException("Document ID must not be null");
        }

        // Rechercher l'abonnement et le document
        Abonnement abonnement = abonnementRepository.findById(empruntDto.getAbonnement().getAbonnementId())
                .orElseThrow(() -> new IllegalArgumentException("Abonnement not found"));
        Document document = documentRepository.findById(empruntDto.getDocument().getDocument_id())
                .orElseThrow(() -> new IllegalArgumentException("Document not found"));

        // Convertir DTO en entité
        Emprunt emprunt = new Emprunt();
        emprunt.setDate_emprunt(empruntDto.getDate_emprunt());
        emprunt.setDate_retour(empruntDto.getDate_retour());
        emprunt.setAbonnement(abonnement);
        emprunt.setDocument(document);

        // Sauvegarder l'emprunt
        emprunt = empruntRepository.save(emprunt);

        // Convertir l'entité en DTO
        return convertEntityToDto(emprunt);
    }

    @Override
    public List<EmpruntDto> getAllEmprunts() {
        return empruntRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmpruntDto> getEmpruntById(Long id) {
        return empruntRepository.findById(id)
                .map(this::convertEntityToDto);
    }

    @Override
    public EmpruntDto updateEmprunt(Long id, EmpruntDto empruntDto) {
        // Vérifier si l'emprunt existe
        Emprunt existingEmprunt = empruntRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Emprunt not found"));

        // Mettre à jour les champs
        if (empruntDto.getDate_emprunt() != null) {
            existingEmprunt.setDate_emprunt(empruntDto.getDate_emprunt());
        }
        if (empruntDto.getDate_retour() != null) {
            existingEmprunt.setDate_retour(empruntDto.getDate_retour());
        }
        if (empruntDto.getAbonnement() != null) {
            Abonnement abonnement = abonnementRepository.findById(empruntDto.getAbonnement().getAbonnementId())
                    .orElseThrow(() -> new IllegalArgumentException("Abonnement not found"));
            existingEmprunt.setAbonnement(abonnement);
        }
        if (empruntDto.getDocument() != null) {
            Document document = documentRepository.findById(empruntDto.getDocument().getDocument_id())
                    .orElseThrow(() -> new IllegalArgumentException("Document not found"));
            existingEmprunt.setDocument(document);
        }

        // Sauvegarder les modifications
        existingEmprunt = empruntRepository.save(existingEmprunt);

        // Convertir l'entité en DTO
        return convertEntityToDto(existingEmprunt);
    }

    @Override
    public void deleteEmprunt(Long id) {
        empruntRepository.deleteById(id);
    }

    private EmpruntDto convertEntityToDto(Emprunt emprunt) {
        EmpruntDto dto = new EmpruntDto();
        dto.setEmprunt_id(emprunt.getEmprunt_id());
        dto.setDate_emprunt(emprunt.getDate_emprunt());
        dto.setDate_retour(emprunt.getDate_retour());

        // Convertir Abonnement en AbonnementDto
        if (emprunt.getAbonnement() != null) {
            Abonnement abonnementDto = new Abonnement();
            abonnementDto.setAbonnementId(emprunt.getAbonnement().getAbonnementId());
            abonnementDto.setDate_expiration(emprunt.getAbonnement().getDate_expiration());
            abonnementDto.setDate_inscription(emprunt.getAbonnement().getDate_inscription());
            abonnementDto.setSolde(emprunt.getAbonnement().getSolde());
            dto.setAbonnement(abonnementDto);
        }

        // Convertir Document en DocumentDto
        if (emprunt.getDocument() != null) {
            Document documentDto = new Document();
            documentDto.setDocument_id(emprunt.getDocument().getDocument_id());
            documentDto.setTitre(emprunt.getDocument().getTitre());
            documentDto.setType(emprunt.getDocument().getType());
            dto.setDocument(documentDto);
        }

        return dto;
    }

    private Emprunt convertDtoToEntity(EmpruntDto empruntDto) {
        Emprunt emprunt = new Emprunt();
        emprunt.setEmprunt_id(empruntDto.getEmprunt_id());
        emprunt.setDate_emprunt(empruntDto.getDate_emprunt());
        emprunt.setDate_retour(empruntDto.getDate_retour());

        // Convertir AbonnementDto en Abonnement
        if (empruntDto.getAbonnement() != null) {
            Abonnement abonnement = abonnementRepository.findById(empruntDto.getAbonnement().getAbonnementId())
                    .orElseThrow(() -> new IllegalArgumentException("Abonnement not found"));
            emprunt.setAbonnement(abonnement);
        }

        // Convertir DocumentDto en Document
        if (empruntDto.getDocument() != null) {
            Document document = documentRepository.findById(empruntDto.getDocument().getDocument_id())
                    .orElseThrow(() -> new IllegalArgumentException("Document not found"));
            emprunt.setDocument(document);
        }

        return emprunt;
    }
}
