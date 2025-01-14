package com.mediatheque.mediatheque.Controller;


import com.mediatheque.mediatheque.Dto.DocumentDto;
import com.mediatheque.mediatheque.Dto.DocumentRequest;
import com.mediatheque.mediatheque.Dto.LivreDto;
import com.mediatheque.mediatheque.Repository.DocumentRepository;
import com.mediatheque.mediatheque.Service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Mediatheque/Document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping(path = "/save")
    public ResponseEntity<String> saveDocument(@RequestBody DocumentRequest documentDto) {
        if (documentDto.getDocument() == null) {
            return new ResponseEntity<>("DocumentDTO is null", HttpStatus.BAD_REQUEST);
        }

        String result = documentService.addDocument(documentDto);

        if (result.equals("Document ajouté avec succès")) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    // Endpoint pour récupérer tous les documents
    @GetMapping(path = "/getAllDocuments")
    public ResponseEntity<List<DocumentDto>> getAllDocuments() {
        List<DocumentDto> allDocuments = documentService.getDocuments();

        if (allDocuments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(allDocuments, HttpStatus.OK);
    }

    // Endpoint pour mettre à jour un document
    @PutMapping(path = "/update")
    public ResponseEntity<String> updateDocument(@RequestBody DocumentRequest documentDTO) {
        if (documentDTO == null) {
            return new ResponseEntity<>("DocumentDTO is null", HttpStatus.BAD_REQUEST);
        }

        String result = documentService.updateDocument(documentDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> getDocumentById(@PathVariable Long id) {
        DocumentDto documentDto = documentService.getDocumentById(id);
        return ResponseEntity.ok(documentDto);
    }

    // Endpoint pour supprimer un document
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long id) {
        String result = documentService.deleteDocument(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}