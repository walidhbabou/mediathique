package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    List<Abonnement> findByDateexpirationBefore(Date dateexpiration);
    Optional<Abonnement> findByLecteurLecteurId(Long lecteurId);
}
