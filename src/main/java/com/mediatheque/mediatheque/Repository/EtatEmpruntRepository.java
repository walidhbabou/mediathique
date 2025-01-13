package com.mediatheque.mediatheque.Repository;
import com.mediatheque.mediatheque.Entity.EtatEmprunt;

import com.mediatheque.mediatheque.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface EtatEmpruntRepository extends JpaRepository<EtatEmprunt, Long> {
    @Modifying
    @Query("SELECT ee FROM EtatEmprunt ee WHERE ee.emprunt.emprunt_id = :empruntId")
    Optional<EtatEmprunt> findByEmprunt_Id(@Param("empruntId") Long empruntId);
    @Query("SELECT ee FROM EtatEmprunt ee WHERE ee.status = :status")
    List<EtatEmprunt> findByStatus(@Param("status") Status status);
    @Query("SELECT ee FROM EtatEmprunt ee WHERE ee.emprunt.lecteur.lecteurId = :lecteurId")
    List<EtatEmprunt> findByEmprunt_Lecteur_Id(@Param("lecteurId") Long lecteurId);}