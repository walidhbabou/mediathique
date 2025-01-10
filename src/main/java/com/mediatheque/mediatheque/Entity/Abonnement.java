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
   private  Date dateexpiration;

    @Column(name = "date_inscription")
    private Date dateinscription;

    @Column(name = "solde",length = 50)
    private Long solde;

    @ManyToOne // Change from @OneToOne to @ManyToOne
    @JoinColumn(name = "lecteur_id")
    private Lecteur lecteur;

}
