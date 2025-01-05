package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@EnableJpaRepositories
@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    @Query("SELECT c FROM Consultation c WHERE c.abonnement.abonnementId = :abonnementId")
    List<Consultation> findByAbonnementId(@Param("abonnementId") Long abonnementId);

    @Query("SELECT c FROM Consultation c WHERE c.document.document_id = :documentId")
    List<Consultation> findByDocumentId(@Param("documentId") Long documentId);
}
