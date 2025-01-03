package com.mediatheque.mediatheque.Repository;
import com.mediatheque.mediatheque.Entity.Journale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
@EnableJpaRepositories
public interface JournaleRepository  extends JpaRepository<Journale,Long>{
    @Modifying
    @Query("DELETE FROM Journale m WHERE m.document.document_id = :documentId")
    void deleteByDocumentId(@Param("documentId") Long documentId);
}
