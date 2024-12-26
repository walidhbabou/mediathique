package com.example.media.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "les-roles")
public class Role {
    private String username ;
    private String role ;
}
