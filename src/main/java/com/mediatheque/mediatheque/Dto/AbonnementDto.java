package com.mediatheque.mediatheque.Dto;

import com.mediatheque.mediatheque.Entity.Lecteur;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter

public class AbonnementDto {
    private Long abonnementId;
    private Date date_expiration;
    private Date date_inscription;
    private Long solde;
    private Lecteur lecteur;

}
