package com.mediatheque.mediatheque.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicInsert
@DynamicUpdate

// Identifiant pour cette sous-classe

public class Lecteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecteur_id")
    private Long lecteur_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
