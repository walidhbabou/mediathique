package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.Poste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@EnableJpaRepositories
@Repository

public interface PosteRepository extends JpaRepository<Poste, Integer> {
    List<Poste> findByEtatTrue();
}
