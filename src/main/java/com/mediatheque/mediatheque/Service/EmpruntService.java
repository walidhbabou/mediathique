package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.EmpruntDto;

import java.util.List;
import java.util.Optional;

public interface EmpruntService {
    EmpruntDto createEmprunt(EmpruntDto empruntDto);
    List<EmpruntDto> getAllEmprunts();
    Optional<EmpruntDto> getEmpruntById(Long id);
    EmpruntDto updateEmprunt(Long id, EmpruntDto empruntDto);
    void deleteEmprunt(Long id);
}
