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
    private Date dateinscription;
    private Date dateexpiration;
    private Long solde;

    private Long lecteurId;
    private String lecteurName;
    private String  lecteurlastName;

}