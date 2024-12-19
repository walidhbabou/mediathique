package com.example.media.Dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {
    private Long id;
    private String titre;
    private String Type;
    private double prix;
    private Boolean consultable;
    private Boolean empruntable;
    private int quantite;
    private int quantiteDisponible;
}
