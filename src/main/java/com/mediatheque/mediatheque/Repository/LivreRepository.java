package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    @Modifying
    @Query("DELETE FROM Livre m WHERE m.document.document_id = :documentId")
    void deleteByDocumentId(@Param("documentId") Long documentId);

}
