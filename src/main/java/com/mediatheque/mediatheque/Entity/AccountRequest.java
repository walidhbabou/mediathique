package com.mediatheque.mediatheque.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AccountRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @Column(length = 500)
    private String message;
}
