package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    @Modifying
    @Query("DELETE FROM Emprunt e WHERE e.document.document_id = :documentId")

    void deleteByDocumentId(@Param("documentId") Long documentId);



}
