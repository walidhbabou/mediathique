package com.mediatheque.mediatheque.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "abonnement")
@Builder
public class Abonnement {

    @Id
    @Column(name = "abonnement_id",length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long abonnementId;

    @Column(name = "date_expiration")
   private  Date date_expiration;

    @Column(name = "date_inscription")
    private Date date_inscription;

    @Column(name = "solde",length = 50)
    private Long solde;

    @OneToOne
    @JoinColumn(name = "lecteur_id")
    private Lecteur lecteur;


}
