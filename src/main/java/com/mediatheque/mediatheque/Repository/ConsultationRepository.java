package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByAbonnementId(Long abonnementId); // Rechercher par abonnement
    List<Consultation> findByDocumentId(Long documentId); // Rechercher par document
}
