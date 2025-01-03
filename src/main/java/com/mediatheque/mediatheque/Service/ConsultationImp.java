package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.ConsultationDto;
import com.mediatheque.mediatheque.Entity.Consultation;
import com.mediatheque.mediatheque.Repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConsultationImp implements ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Override
    public List<ConsultationDto> getAllConsultations() {
        return consultationRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ConsultationDto getConsultationById(Long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation not found"));
        return convertToDto(consultation);
    }

    @Override
    public List<ConsultationDto> getConsultationsByAbonnement(Long abonnementId) {
        return consultationRepository.findByAbonnementId(abonnementId)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ConsultationDto> getConsultationsByDocument(Long documentId) {
        return consultationRepository.findByDocumentId(documentId)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ConsultationDto saveConsultation(ConsultationDto consultationDto) {
        Consultation consultation = convertToEntity(consultationDto);
        Consultation savedConsultation = consultationRepository.save(consultation);
        return convertToDto(savedConsultation);
    }

    @Override
    public ConsultationDto updateConsultation(Long id, ConsultationDto consultationDto) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation not found"));
        consultation.setDate_consult(consultationDto.getDate_consult());
        consultation.setDate_expiration(consultationDto.getDate_expiration());
        consultation.setAbonnement(consultationDto.getAbonnement());
        consultation.setDocument(consultationDto.getDocument());
        Consultation updatedConsultation = consultationRepository.save(consultation);
        return convertToDto(updatedConsultation);
    }

    @Override
    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
    }

    private ConsultationDto convertToDto(Consultation consultation) {
        ConsultationDto dto = new ConsultationDto();
        dto.setConsult_id(consultation.getConsult_id());
        dto.setDate_consult(consultation.getDate_consult());
        dto.setDate_expiration(consultation.getDate_expiration());
        dto.setAbonnement(consultation.getAbonnement());
        dto.setDocument(consultation.getDocument());
        return dto;
    }

    private Consultation convertToEntity(ConsultationDto dto) {
        Consultation consultation = new Consultation();
        consultation.setConsult_id(dto.getConsult_id());
        consultation.setDate_consult(dto.getDate_consult());
        consultation.setDate_expiration(dto.getDate_expiration());
        consultation.setAbonnement(dto.getAbonnement());
        consultation.setDocument(dto.getDocument());
        return consultation;
    }
}

