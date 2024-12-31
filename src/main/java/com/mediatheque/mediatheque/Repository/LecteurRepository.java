package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.Lecteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface LecteurRepository extends JpaRepository<Lecteur, Long> {
}
