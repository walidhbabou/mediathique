package com.mediatheque.mediatheque.Dto;

import lombok.Data;

@Data
public class DocumentRequest {
    private DocumentDto document;
    private LivreDto livre;
}

