package com.mediatheque.mediatheque.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "reclamation")
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reclamation_id")
    private Long reclamationId;

    @Column(name = "message")
    private String message;

    @Column(name = "date_reclamation")
    private Date dateReclamation;

    @ManyToOne
    @JoinColumn(name = "emprunt_id")
    private Emprunt emprunt;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;
}