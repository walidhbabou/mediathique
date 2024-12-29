package com.mediatheque.mediatheque.Dto;

import com.mediatheque.mediatheque.Entity.Abonnement;
import com.mediatheque.mediatheque.Entity.Document;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter

public class EmpruntDto {
    private Long emprunt_id;
    Date date_emprunt;
    Date date_retour;
    private Abonnement abonnement;
    private Document document;
}
