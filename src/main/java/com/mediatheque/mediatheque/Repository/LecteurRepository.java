package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.Lecteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface LecteurRepository extends JpaRepository<Lecteur, Long> {
    // Rechercher un lecteur par l'ID de l'utilisateur
    @Query("SELECT l FROM Lecteur l WHERE l.user.id= :userId")
    Optional<Lecteur> findByUserId(@Param("userId") Long userId);

    // Autres méthodes existantes
    List<Lecteur> findAll();
    Optional<Lecteur> findById(Long id);
}
