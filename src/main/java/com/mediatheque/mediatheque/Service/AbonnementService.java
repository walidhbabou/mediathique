package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.AbonnementDto;
import com.mediatheque.mediatheque.Entity.Abonnement;

import java.util.Date;
import java.util.List;

public interface AbonnementService {
    AbonnementDto createAbonnement(AbonnementDto abonnementDto);
    AbonnementDto updateAbonnement(Long id, AbonnementDto abonnementDto);
    void deleteAbonnement(Long id);
    AbonnementDto getAbonnementById(Long id);
    List<AbonnementDto> getAllAbonnements();
    List<AbonnementDto> findAbonnementsExpiringBefore(Date date_expiration);
    AbonnementDto getAbonnementByLecteurId(Long lecteurId);

}
