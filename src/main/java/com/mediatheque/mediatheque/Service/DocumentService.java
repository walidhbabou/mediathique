package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.DocumentDto;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface DocumentService  {
    String addDocument(DocumentDto document);
    List<DocumentDto> getDocuments();
    String updateDocument(DocumentDto document);
    String deleteDocument(Long id);
    DocumentDto getDocumentById(Long id); // Nouvelle méthode


}
