package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.JournaleDto;
import com.mediatheque.mediatheque.Dto.LivreDto;
import com.mediatheque.mediatheque.Entity.Document;
import com.mediatheque.mediatheque.Entity.Journale;
import com.mediatheque.mediatheque.Entity.Livre;
import com.mediatheque.mediatheque.Repository.DocumentRepository;
import com.mediatheque.mediatheque.Repository.JournaleRepository;
import com.mediatheque.mediatheque.Repository.LivreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class JournaleImp implements JournaleService {

    @Autowired
    private JournaleRepository journaleRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public String addJournale(JournaleDto journaleDto) {
        if (journaleDto == null || journaleDto.getDocument() == null) {
            return "Le journal ou le document est invalide.";
        }

        Document document = journaleDto.getDocument();
        if (document.getDocument_id() == null) {
            document = documentRepository.save(document); // Sauvegarder le document s'il est nouveau
        } else {
            Optional<Document> existingDocument = documentRepository.findById(document.getDocument_id());
            if (!existingDocument.isPresent()) {
                return "Document non trouvé.";
            }
            document = existingDocument.get(); // Utiliser le document existant
        }

        Journale journale = new Journale();
        journale.setDocument(document);

        journaleRepository.save(journale);
        return "Journal ajouté avec succès.";
    }

    @Override
    public List<JournaleDto> getJournales() {
        List<Journale> journales = journaleRepository.findAll();
        return journales.stream()
                .map(this::convertToJournaleDto)
                .collect(Collectors.toList());
    }

    private JournaleDto convertToJournaleDto(Journale journale) {
        JournaleDto journaleDto = new JournaleDto();
        journaleDto.setJournal_id(journale.getJournal_id());
        journaleDto.setDocument(journale.getDocument());
        return journaleDto;
    }

    @Override
    @Transactional
    public String updateJournale(JournaleDto journaleDto) {
        Optional<Journale> journaleOptional = journaleRepository.findById(journaleDto.getJournal_id());
        if (!journaleOptional.isPresent()) {
            return "Journal non trouvé.";
        }

        Journale journale = journaleOptional.get();
        Document newDocument = journaleDto.getDocument();

        if (newDocument != null) {
            Document existingDocument = journale.getDocument();

            // Mettre à jour les champs du document existant
            if (existingDocument != null) {
                existingDocument.setTitre(newDocument.getTitre());
                existingDocument.setType(newDocument.getType());
                existingDocument.setPrix(newDocument.getPrix());
                existingDocument.setConsultable(newDocument.getConsultable());

                existingDocument.setQuantite(newDocument.getQuantite());
                existingDocument.setQuantite_disponible(newDocument.getQuantite_disponible());

                documentRepository.save(existingDocument); // Sauvegarder les modifications
            } else {
                // Si le document n'existe pas, enregistrez-le
                documentRepository.save(newDocument);
                journale.setDocument(newDocument);
            }
        }

        journaleRepository.save(journale);
        return "Journal mis à jour avec succès.";
    }

    @Override
    public String deleteJournaleById(Long id) {
        Optional<Journale> journaleOptional = journaleRepository.findById(id);
        if (!journaleOptional.isPresent()) {
            return "Journal non trouvé.";
        }

        journaleRepository.deleteById(id);
        return "Journal supprimé avec succès.";
    }


    @Override
    public JournaleDto getJournaleById(Long id) {
        // Vérification de la validité de l'identifiant
        if (id == null) {
            throw new IllegalArgumentException("L'identifiant ne peut pas être null.");
        }

        // Recherche du journal par son ID
        Optional<Journale> journaleOptional = journaleRepository.findById(id);

        // Si le journal n'existe pas, lever une exception
        Journale journale = journaleOptional.orElseThrow(() ->
                new EntityNotFoundException("Journal introuvable pour l'ID : " + id));

        // Convertir l'entité Journale en DTO et retourner le résultat
        return convertToJournaleDto(journale);
    }



}