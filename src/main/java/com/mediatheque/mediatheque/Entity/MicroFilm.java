package com.mediatheque.mediatheque.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "micro_film")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
// Identifiant pour cette sous-classe

public class MicroFilm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment;
    @Column(name = "micro_id")
    private Long micro_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private Document document;
}
