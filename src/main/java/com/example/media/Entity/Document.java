package com.example.media.Entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Getter
@Setter
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long id;
    @Column(name = "titre")
    private String titre;
    @Column(name = "type")
    private String type;
    @Column(name = "prix")
    private double prix;
    @Column(name = "consultable")
    private Boolean consultable;
    @Column(name = "empruntable")
    private Boolean empruntable;
    @Column(name = "quantite")
    private int quantite;
    @Column(name = "quantite_disponible")
    private int quantite_disponible;
}
