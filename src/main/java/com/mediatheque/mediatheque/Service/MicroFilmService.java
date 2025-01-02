package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.MicroFilmDto;

import java.util.List;
import java.util.Optional;

public interface MicroFilmService {

    List<MicroFilmDto> getAllMicroFilms();

    Optional<MicroFilmDto> getMicroFilmById(Long id);

    MicroFilmDto createMicroFilm(MicroFilmDto microFilmDto);

    void deleteMicroFilm(Long id);
    String updateMicroFilm(Long microFilmId, MicroFilmDto microFilmDto);
}