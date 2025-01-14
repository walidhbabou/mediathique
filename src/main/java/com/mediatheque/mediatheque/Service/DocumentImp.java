package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.DocumentDto;
import com.mediatheque.mediatheque.Dto.DocumentRequest;
import com.mediatheque.mediatheque.Dto.LivreDto;
import com.mediatheque.mediatheque.Entity.Document;

import com.mediatheque.mediatheque.Entity.Livre;
import com.mediatheque.mediatheque.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentImp implements DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private EmpruntRepository empruntRepository;
    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private LivreService livreService ;

    @Override
    public String addDocument(DocumentRequest documentDto) {
        Document document = new Document();
        document.setTitre(documentDto.getDocument().getTitre());
        document.setType(documentDto.getDocument().getType());
        document.setConsultable(documentDto.getDocument().getConsultable());
        document.setPrix(documentDto.getDocument().getPrix());
        document.setQuantite(documentDto.getDocument().getQuantite());
        document.setQuantite_disponible(documentDto.getDocument().getQuantite_disponible());
        System.out.println(document);

        // Traitement spécifique pour les livres
        if (document.getType().equals("LIVRE")) {
            LivreDto livre = new LivreDto();
            livre.setDocument(document);
            livre.setAuteur(documentDto.getLivre().getAuteur());
            livreService.addLivre(livre);
        }

        // Enregistrer le document dans la base de données
        documentRepository.save(document);
        return "Document ajouté avec succès";
    }


    @Override
    public List<DocumentDto> getDocuments() {
        return documentRepository.findAll().stream().map(document -> {
            DocumentDto dto = new DocumentDto();
            dto.setDocument_id(document.getDocument_id());
            dto.setTitre(document.getTitre());
            dto.setType(document.getType());
            dto.setConsultable(document.getConsultable());
            dto.setPrix(document.getPrix());
            dto.setQuantite(document.getQuantite());
            dto.setQuantite_disponible(document.getQuantite_disponible());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public String updateDocument(DocumentRequest documentDto) {
        // Fetch the existing document from the database
        Optional<Document> optionalDocument = documentRepository.findById(documentDto.getDocument().getDocument_id());
        if (!optionalDocument.isPresent()) {
            return "Document non trouvé";
        }

        Document document = optionalDocument.get();

        // Update the document fields
        document.setTitre(documentDto.getDocument().getTitre());
        document.setType(documentDto.getDocument().getType());
        document.setConsultable(documentDto.getDocument().getConsultable());
        document.setPrix(documentDto.getDocument().getPrix());
        document.setQuantite(documentDto.getDocument().getQuantite());
        document.setQuantite_disponible(documentDto.getDocument().getQuantite_disponible());

        // If the document type is "LIVRE", update the associated LivreDto
        if (document.getType().equals("LIVRE")) {
            Optional<LivreDto> optionalLivre = Optional.ofNullable(livreService.getLivreById(document.getDocument_id()));
            if (optionalLivre.isPresent()) {
                LivreDto livre = optionalLivre.get();
                livre.setAuteur(documentDto.getLivre().getAuteur());
                livreService.updateLivre(livre);
            } else {
                // If no LivreDto exists, create a new one
                LivreDto livre = new LivreDto();
                livre.setDocument(document);
                livre.setAuteur(documentDto.getLivre().getAuteur());
                livreService.addLivre(livre);
            }
        }

        // Save the updated document
        documentRepository.save(document);

        return "Document mis à jour avec succès";
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public String deleteDocument(Long id) {
        Optional<Document> optionalDocument = documentRepository.findById(id);
        if (!optionalDocument.isPresent()) {
            return "Document non trouvé";
        }
        empruntRepository.deleteByDocumentId(id);
        livreRepository.deleteByDocumentId(id);
        documentRepository.deleteById(id);

        return "Document supprimé avec succès";
    }
    @Override
    public DocumentDto getDocumentById(Long id) {
        Optional<Document> optionalDocument = documentRepository.findById(id);
        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            DocumentDto dto = new DocumentDto();
            dto.setDocument_id(document.getDocument_id());
            dto.setTitre(document.getTitre());
            dto.setType(document.getType());
            dto.setConsultable(document.getConsultable());
            dto.setPrix(document.getPrix());

            dto.setQuantite(document.getQuantite());
            dto.setQuantite_disponible(document.getQuantite_disponible());
            return dto;
        } else {
            throw new RuntimeException("Document non trouvé avec l'ID : " + id);
        }
    }
}