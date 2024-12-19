package com.example.media.Controller;

import com.example.media.Dto.DocumentDto;
import com.example.media.Service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    // Endpoint to add a document
    @PostMapping(path = "/save")
    public ResponseEntity<String> addDocument(@RequestBody DocumentDto documentDto) {
        String response = documentService.addDocument(documentDto);
        return ResponseEntity.ok(response);
    }

    // Endpoint to get all documents
    @GetMapping(path = "/getAllDocuments")
    public ResponseEntity<List<DocumentDto>> getDocuments() {
        List<DocumentDto> documents = documentService.getDocuments();
        return ResponseEntity.ok(documents);
    }

    // Endpoint to update a document
    // Endpoint to update a document
    @PutMapping(path = "/update")
    public ResponseEntity<String> updateDocument(@RequestBody DocumentDto documentDto) {
        String response = documentService.updateDocument(documentDto);
        return ResponseEntity.ok(response);
    }

    // Endpoint to delete a document by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long id) {
        String response = documentService.deleteDocument(id);
        return ResponseEntity.ok(response);
    }
}