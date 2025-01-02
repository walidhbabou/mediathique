package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.JournaleDto;
import com.mediatheque.mediatheque.Dto.LivreDto;
import com.mediatheque.mediatheque.Entity.Journale;
import com.mediatheque.mediatheque.Entity.Livre;
import com.mediatheque.mediatheque.Repository.JournaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JournaleImp implements JournaleService {

    @Autowired
    private JournaleRepository journaleRepository;

    @Override
    public String addJournale(JournaleDto journaleDto) {
        if (journaleDto == null || journaleDto.getDocument() == null) {
            return "Le journal ou le document est invalide.";
        }

        // Créer un nouvel objet Journale à partir du DTO
        Journale journale = new Journale();
        journale.setDocument(journaleDto.getDocument());  // Assurez-vous que le DTO contient le document

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
        Optional<Journale> journaleOptional = journaleRepository.findById(journaleDto.getJournal_id());
        if (!journaleOptional.isPresent()) {
            return "Journale non trouvé";
        }
        Journale journale = journaleOptional.get();
        journale.setDocument(journaleDto.getDocument());
        journaleRepository.save(journale);

        return "Journale mis à jour avec succès";
    }

}
