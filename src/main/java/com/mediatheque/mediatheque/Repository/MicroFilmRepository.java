package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.MicroFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MicroFilmRepository extends JpaRepository<MicroFilm, Long> {
    List<MicroFilm> findByDocument_Id(Long documentId); // Recherche les microfilms par ID du document

}
