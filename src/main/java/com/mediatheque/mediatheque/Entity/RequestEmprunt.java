package com.mediatheque.mediatheque.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import com.mediatheque.mediatheque.model.Status;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "requestemprunt")
@AllArgsConstructor
@NoArgsConstructor
public class RequestEmprunt {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @Column(name = "date_request")
    private Date dateRequest;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "lecteur_id")
    private Lecteur lecteur;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;
}