package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.MicroFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MicroFilmRepository extends JpaRepository<MicroFilm, Long> {

}
