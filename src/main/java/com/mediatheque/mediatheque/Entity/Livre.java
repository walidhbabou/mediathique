package com.mediatheque.mediatheque.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "livre")
 // Identifiant pour cette sous-classe

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Livre  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment;
    @Column(name = "livre_id")
    private Long livre_id;

    @Column(name = "auteur")
    String auteur;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private Document document;


}
