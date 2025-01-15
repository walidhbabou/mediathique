package com.mediatheque.mediatheque.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReclamationDto {
    private Long reclamationId;
    private String message;
    private Date dateReclamation;
    private Long empruntId; // ID de l'emprunt concerné
    private Long employeId; // ID de l'employé qui envoie la réclamation
}