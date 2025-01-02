package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.JournaleDto;
import com.mediatheque.mediatheque.Dto.LivreDto;
import com.mediatheque.mediatheque.Entity.Document;
import com.mediatheque.mediatheque.Entity.Journale;
import com.mediatheque.mediatheque.Entity.Livre;
import com.mediatheque.mediatheque.Repository.DocumentRepository;
import com.mediatheque.mediatheque.Repository.JournaleRepository;
import com.mediatheque.mediatheque.Repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        // Sauvegarder le document reçu pour obtenir un ID
        Document savedDocument = documentRepository.save(journaleDto.getDocument());

        // Créer un nouvel objet Journale
        Journale journale = new Journale();
        journale.setDocument(savedDocument);  // Associez le document sauvegardé au journal

        // Sauvegarder le journal dans la base de données
        journaleRepository.save(journale);

        return "Journal ajouté avec succès.";
    }



    @Override
    public List<JournaleDto> getJournales() {
        List<Journale> journales = journaleRepository.findAll();
        return journales.stream()
                .map(journale -> convertToJournaleDto(journale))
                .collect(Collectors.toList());
    }
    private JournaleDto convertToJournaleDto(Journale journale) {
        JournaleDto journaleDto = new JournaleDto();

        // Utiliser l'objet journale pour accéder aux propriétés
        journaleDto.setJournal_id(journale.getJournal_id());  // Récupérer l'ID du journale
        journaleDto.setDocument(journale.getDocument());  // Récupérer le document associé au journale

        return journaleDto;
    }

    @Override
    public String updateJournale(JournaleDto journaleDto) {
        // Vérifiez si le journal existe dans la base de données
        Optional<Journale> journaleOptional = journaleRepository.findById(journaleDto.getJournal_id());
        if (!journaleOptional.isPresent()) {
            return "Journal non trouvé.";
        }
        Journale journale = journaleOptional.get();

        Document document = journaleDto.getDocument();
        if (document != null) {
            // Si un document est passé, mettez à jour l'objet document associé au journal
            document.setDocument_id(journale.getDocument().getDocument_id());  // Gardez le même ID pour la mise à jour
            documentRepository.save(document);  // Sauvegarder le document mis à jour
        }

        journale.setDocument(document);  // Associer le document mis à jour au journal

        journaleRepository.save(journale);

        return "Journal mis à jour avec succès.";
    }


}
