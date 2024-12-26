package com.example.media.Entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Enabled
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "compte")
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compte_id")
    private long Id ;
    private User user ;
}
