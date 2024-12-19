package com.example.media.Repository;

import com.example.media.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
@EnableJpaRepositories
@Repository
public interface Documentrepository extends JpaRepository <Document, Long>{
}
