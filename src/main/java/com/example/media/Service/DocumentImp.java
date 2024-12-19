package com.example.media.Service;

import com.example.media.Dto.DocumentDto;
import com.example.media.Entity.Document;
import com.example.media.Repository.Documentrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentImp implements DocumentService {

    @Autowired
    private Documentrepository documentrepository;

    @Override
    public String addDocument(DocumentDto documentDto) {
        Document document = new Document();
        document.setTitre(documentDto.getTitre());
        document.setType(documentDto.getType());
        document.setConsultable(documentDto.getConsultable());
        document.setPrix(documentDto.getPrix());
        document.setEmpruntable(documentDto.getEmpruntable());
        document.setQuantite(documentDto.getQuantite());
        document.setQuantite_disponible(documentDto.getQuantiteDisponible());
        documentrepository.save(document);

        return "Document ajouté avec succès";
    }

    @Override
    public List<DocumentDto> getDocuments() {
        return documentrepository.findAll().stream().map(document -> {
            DocumentDto dto = new DocumentDto();
            dto.setId(document.getId());
            dto.setTitre(document.getTitre());
            dto.setType(document.getType());
            dto.setConsultable(document.getConsultable());
            dto.setPrix(document.getPrix());
            dto.setEmpruntable(document.getEmpruntable());
            dto.setQuantite(document.getQuantite());
            dto.setQuantiteDisponible(document.getQuantite_disponible());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public String updateDocument(DocumentDto documentDto) {
        if (documentDto.getId() == null) {
            return "L'ID du document ne peut pas être null";
        }

        Document document = documentrepository.findById(documentDto.getId()).orElse(null);
        if (document == null) {
            return "Document introuvable";
        }

        document.setTitre(documentDto.getTitre());
        document.setType(documentDto.getType());
        document.setConsultable(documentDto.getConsultable());
        document.setPrix(documentDto.getPrix());
        document.setEmpruntable(documentDto.getEmpruntable());
        document.setQuantite(documentDto.getQuantite());
        document.setQuantite_disponible(documentDto.getQuantiteDisponible());
        documentrepository.save(document);

        return "Document mis à jour avec succès";
    }

    @Override
    public String deleteDocument(Long id) {
        if (!documentrepository.existsById(id)) {
            return "Document introuvable";
        }
        documentrepository.deleteById(id);
        return "Document supprimé avec succès";
    }
}
