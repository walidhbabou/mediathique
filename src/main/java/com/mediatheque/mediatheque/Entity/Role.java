package com.mediatheque.mediatheque.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable

public class Role {
    @Column(name = "username")
    private String username ;
    @Column(name = "typeRole")
    private String typeRole ;


}
