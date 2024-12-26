package com.mediatheque.mediatheque.Dto;

import lombok.*;

@Data
@Getter
@Setter
public class DocumentDto {
private Long Document_id;
private String titre;
private String Type;
    private double prix;
    private Boolean consultable;
    private Boolean empruntable;
    private int quantite;
    private int quantite_disponible;

}
