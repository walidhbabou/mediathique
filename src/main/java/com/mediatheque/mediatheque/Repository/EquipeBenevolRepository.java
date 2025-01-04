package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.EquipeBenevol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@EnableJpaRepositories
@Repository
public interface EquipeBenevolRepository extends JpaRepository<EquipeBenevol, Long> {

}
