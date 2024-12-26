package com.mediatheque.mediatheque.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long admin_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

