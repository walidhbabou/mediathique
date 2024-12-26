package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
@EnableJpaRepositories
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
