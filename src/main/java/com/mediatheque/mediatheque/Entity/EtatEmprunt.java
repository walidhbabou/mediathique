package com.mediatheque.mediatheque.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import com.mediatheque.mediatheque.model.Status;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "etat_emprunt")
public class EtatEmprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Relation Many-to-One avec Emprunt
    @JoinColumn(name = "emprunt_id")
    private Emprunt emprunt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "date_modification")
    private LocalDate dateModification;

    // Constructeurs
    public EtatEmprunt() {}

    public EtatEmprunt(Emprunt emprunt, Status status) {
        this.emprunt = emprunt;
        this.status = status;
        this.dateModification = LocalDate.now();
    }

    // Getters et Setters
    // ... (les mêmes que précédemment)
}