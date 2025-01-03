package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.ConsultationDto;

import java.util.List;

public interface ConsultationService {
    List<ConsultationDto> getAllConsultations();
    ConsultationDto getConsultationById(Long id);
    List<ConsultationDto> getConsultationsByAbonnement(Long abonnementId);
    List<ConsultationDto> getConsultationsByDocument(Long documentId);
    ConsultationDto saveConsultation(ConsultationDto consultationDto);
    ConsultationDto updateConsultation(Long id, ConsultationDto consultationDto);
    void deleteConsultation(Long id);
}
