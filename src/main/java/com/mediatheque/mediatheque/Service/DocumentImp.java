package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.DocumentDto;
import com.mediatheque.mediatheque.Entity.Document;

import com.mediatheque.mediatheque.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
     private MicroFilmRepository microFilmRepository;
     @Autowired
      private LivreRepository livreRepository;
     @Autowired
     private JournaleRepository journaleRepository;
     @Autowired
     private CdRomRepository cdRomRepository;
    @Override
    public String addDocument(DocumentDto documentDto) {
        Document document = new Document();
        document.setTitre(documentDto.getTitre());
        document.setType(documentDto.getType());
        document.setConsultable(documentDto.getConsultable());
        document.setPrix(documentDto.getPrix());
        document.setEmpruntable(documentDto.getEmpruntable());
        document.setQuantite(documentDto.getQuantite());
        document.setQuantite_disponible(documentDto.getQuantite_disponible());
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
            dto.setEmpruntable(document.getEmpruntable());
            dto.setQuantite(document.getQuantite());
            dto.setQuantite_disponible(document.getQuantite_disponible());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public String updateDocument(DocumentDto documentDTO) {
        Optional<Document> optionalDocument = documentRepository.findById(documentDTO.getDocument_id());
        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            document.setTitre(documentDTO.getTitre());
            document.setType(documentDTO.getType());
            document.setPrix(documentDTO.getPrix());
            document.setConsultable(documentDTO.getConsultable());
            document.setEmpruntable(documentDTO.getEmpruntable());
            document.setQuantite(documentDTO.getQuantite());
            document.setQuantite_disponible(documentDTO.getQuantite_disponible());
            documentRepository.save(document);
            return "Document mis à jour avec succès";
        } else {
            return "Document non trouvé";
        }
    }


    @Override
    @Transactional // Assurez-vous que la méthode est exécutée dans une transaction
    public String deleteDocument(Long id) {
        Optional<Document> optionalDocument = documentRepository.findById(id);
        if (!optionalDocument.isPresent()) {
            return "Document non trouvé";
        }
        empruntRepository.deleteByDocumentId(id);
        microFilmRepository.deleteByDocumentId(id);
        livreRepository.deleteByDocumentId(id);
        journaleRepository.deleteByDocumentId(id);
        cdRomRepository.deleteByDocumentId(id);
        documentRepository.deleteById(id);

        return "Document supprimé avec succès";
    }
}