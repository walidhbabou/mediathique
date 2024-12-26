package com.example.media.Entity;

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
public class CdRom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment;
    @Column(name = "cd_id")
    private Integer cd_id;


    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;
}

