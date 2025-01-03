package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.MicroFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface MicroFilmRepository extends JpaRepository<MicroFilm, Long> {
    @Modifying
    @Query("DELETE FROM MicroFilm m WHERE m.document.document_id = :documentId")
    void deleteByDocumentId(@Param("documentId") Long documentId);

}
