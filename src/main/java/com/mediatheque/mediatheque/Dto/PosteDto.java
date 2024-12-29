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

public class PosteDto {
    private Integer posteId;
    private int numero;
    private boolean etat;
    private Date dateConsult;
    private Date dateExpiration;
    private Abonnement abonnement;
    private Document document;
}
