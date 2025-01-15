package com.mediatheque.mediatheque.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicInsert
@DynamicUpdate



public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employe_id")
    private Long employe_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference // Côté maître

    private User user;
}

