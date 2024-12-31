package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {

}
