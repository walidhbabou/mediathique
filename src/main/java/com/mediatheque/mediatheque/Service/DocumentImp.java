package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.DocumentDto;
import com.mediatheque.mediatheque.Entity.Document;

import com.mediatheque.mediatheque.Repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentImp implements DocumentService {
     @Autowired
     private DocumentRepository documentRepository;
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
    public String updateDocument(com.mediatheque.mediatheque.Dto.DocumentDto document) {
        return "";
    }

    @Override
    public String deleteDocument(Long id) {
        return "";
    }
}