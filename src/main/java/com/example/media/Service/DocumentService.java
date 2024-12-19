package com.example.media.Service;

import com.example.media.Dto.DocumentDto;

import java.util.List;

public interface DocumentService {
    String addDocument(DocumentDto document);
    List<DocumentDto> getDocuments();
    String updateDocument(DocumentDto document);
    String deleteDocument(Long id);
}
