package com.mediatheque.mediatheque.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long document_id;
    @Column(name = "titre")
    private String titre;
    @Column(name = "type")
    private String type;
    @Column(name = "prix")
    private double prix;
    @Column(name = "consultable")
    private Boolean consultable;
    @Column(name = "quantite")
    private int quantite;
    @Column(name = "quantite_disponible")
    private int quantite_disponible;

    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public int getQuantite_disponible() {
        return quantite_disponible;
    }

    public void setQuantite_disponible(int quantite_disponible) {
        this.quantite_disponible = quantite_disponible;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    public double getPrix() {
        return prix;
    }
    public void setConsultable(Boolean consultable) {
        this.consultable = consultable;
    }
    public Boolean getConsultable() {
        return consultable;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getTitre() {
        return titre;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Long getDocument_id() {
        return document_id;
    }
    @OneToOne(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private Journale journale;
    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Emprunt> emprunts = new ArrayList<>();

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consultation> consultations;
}
