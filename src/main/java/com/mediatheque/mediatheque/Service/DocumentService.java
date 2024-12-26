package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.DocumentDto;


import java.util.List;

public interface DocumentService  {
    String addDocument(DocumentDto document);
    List<DocumentDto> getDocuments();
    String updateDocument(DocumentDto document);
    String deleteDocument(Long id);
}
