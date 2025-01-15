package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
    @Query("SELECT r FROM Reclamation r WHERE r.emprunt.abonnement.lecteur.lecteurId = :lecteurId")
    List<Reclamation> findByLecteurId(@Param("lecteurId") Long lecteurId);
}

