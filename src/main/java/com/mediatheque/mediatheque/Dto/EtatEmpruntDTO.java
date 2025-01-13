package com.mediatheque.mediatheque.Dto;
import com.mediatheque.mediatheque.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtatEmpruntDTO {
    private Long id;
    private Long empruntId;
    private Status status;
    private LocalDate dateModification;

    // Pour inclure des informations supplémentaires utiles
    private String titreLivre;
    private String nomLecteur;
    private LocalDate dateEmprunt;
}