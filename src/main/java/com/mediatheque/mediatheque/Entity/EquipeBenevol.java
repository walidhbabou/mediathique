package com.mediatheque.mediatheque.Entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "equipe_benevol")
public class EquipeBenevol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "benevol_id")
    private Long benevol_id;

    @Column(name = "equipe_id")
    private Long equipe_id;


    @OneToOne
    @JoinColumn(name = "lecteur_id")
    private Lecteur lecteur;
}
