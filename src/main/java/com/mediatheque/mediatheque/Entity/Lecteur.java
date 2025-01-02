package com.mediatheque.mediatheque.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@DynamicInsert
@DynamicUpdate

// Identifiant pour cette sous-classe

public class Lecteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecteur_id")
    private Long lecteurId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "lecteur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Abonnement> abonnements = new ArrayList<>();


}
