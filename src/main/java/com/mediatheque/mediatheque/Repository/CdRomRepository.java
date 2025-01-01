package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.CdRom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface CdRomRepository extends JpaRepository<CdRom, Long> {
}