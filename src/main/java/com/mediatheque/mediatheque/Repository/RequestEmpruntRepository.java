package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.RequestEmprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestEmpruntRepository extends JpaRepository<RequestEmprunt, Long> {
}