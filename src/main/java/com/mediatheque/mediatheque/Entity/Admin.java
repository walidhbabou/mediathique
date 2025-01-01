package com.mediatheque.mediatheque.Entity;

import jakarta.persistence.*;
import lombok.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                '}';
    }
}

