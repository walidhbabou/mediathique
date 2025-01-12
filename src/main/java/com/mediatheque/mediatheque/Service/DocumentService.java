package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.DocumentDto;
import com.mediatheque.mediatheque.Dto.DocumentRequest;
import com.mediatheque.mediatheque.Dto.LivreDto;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface DocumentService  {
    String addDocument(DocumentRequest documentDto);
    List<DocumentDto> getDocuments();
    String updateDocument(DocumentDto document);
    String deleteDocument(Long id);
    DocumentDto getDocumentById(Long id); // Nouvelle méthode


}
