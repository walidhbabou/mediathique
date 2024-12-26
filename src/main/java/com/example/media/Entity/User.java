package com.example.media.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User  implements Serializable {


    private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id-user")
    private  Long Id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "status")
    //Actif/Inactif
    private String status;
    private String role;







}

