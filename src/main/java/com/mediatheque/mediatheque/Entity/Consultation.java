package com.mediatheque.mediatheque.Entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "consult")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consult_id")
    private Long consult_id;



    @Column(name = "date_consult")
    Date date_consult;

    @Column(name = "date_expiration")
    Date date_expiration;




    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "abonnement_id")
    private Abonnement abonnement;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private Document document;




}
