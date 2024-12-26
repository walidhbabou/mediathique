package com.mediatheque.mediatheque.Controller;


import com.mediatheque.mediatheque.Dto.DocumentDto;
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

    // Endpoint pour sauvegarder un document
    @PostMapping(path = "/save")
    public ResponseEntity<String> saveDocument(@RequestBody DocumentDto documentDTO) {
        if (documentDTO == null) {
            return new ResponseEntity<>("DocumentDTO is null", HttpStatus.BAD_REQUEST);
        }

        String result = documentService.addDocument(documentDTO);

        if (result.equals("Document added successfully")) {
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
    public ResponseEntity<String> updateDocument(@RequestBody DocumentDto documentDTO) {
        if (documentDTO == null) {
            return new ResponseEntity<>("DocumentDTO is null", HttpStatus.BAD_REQUEST);
        }

        String result = documentService.updateDocument(documentDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Endpoint pour supprimer un document
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long id) {
        String result = documentService.deleteDocument(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}