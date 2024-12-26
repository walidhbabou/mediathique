package com.mediatheque.mediatheque.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "cd-rom")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// Identifiant pour cette sous-classe

public class CdRom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment;
    @Column(name = "cd_id")
    private Long cd_id;


    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;
}
