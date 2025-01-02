package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.AbonnementDto;
import com.mediatheque.mediatheque.Entity.Abonnement;

import java.util.List;

public interface AbonnementService {
    AbonnementDto createAbonnement(AbonnementDto abonnementDto);
    AbonnementDto updateAbonnement(Long id, AbonnementDto abonnementDto);
    void deleteAbonnement(Long id);
    AbonnementDto getAbonnementById(Long id);
    List<AbonnementDto> getAllAbonnements();
}
