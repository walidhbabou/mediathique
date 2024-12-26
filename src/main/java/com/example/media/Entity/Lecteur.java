package com.example.media.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "lecteur")
public class Lecteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment;
    @Column(name = "lecteur_id")
    private Integer lecteur_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
