package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.Achat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface AchatRepository extends JpaRepository<Achat, Long> {
    // Vous pouvez ajouter des requêtes spécifiques si nécessaire
}

