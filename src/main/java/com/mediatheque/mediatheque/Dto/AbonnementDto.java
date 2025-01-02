package com.mediatheque.mediatheque.Dto;


import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AbonnementDto {
    private Long abonnementId;
    private Date date_inscription;
    private Date date_expiration;
    private Long solde;

    private Long lecteurId;
}