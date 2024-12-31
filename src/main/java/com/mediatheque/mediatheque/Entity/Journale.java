package com.mediatheque.mediatheque.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "journal")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Journale  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journal_id")
    private Long  journal_id;
//une entité a une relation avec une autre
    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;
}