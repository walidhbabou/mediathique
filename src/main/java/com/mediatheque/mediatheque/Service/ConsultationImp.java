package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.ConsultationDto;
import com.mediatheque.mediatheque.Entity.Abonnement;
import com.mediatheque.mediatheque.Entity.Consultation;
import com.mediatheque.mediatheque.Entity.Document;
import com.mediatheque.mediatheque.Repository.AbonnementRepository;
import com.mediatheque.mediatheque.Repository.ConsultationRepository;
import com.mediatheque.mediatheque.Repository.DocumentRepository;
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
@Autowired
private DocumentRepository documentRepository;
@Autowired
private AbonnementRepository abonnementRepository;
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
        // Convertir le DTO en entité
        Consultation consultation = new Consultation();
        consultation.setDate_consult(consultationDto.getDate_consult());
        consultation.setDate_expiration(consultationDto.getDate_expiration());

        // Récupérer l'abonnement et le document
        Abonnement abonnement = abonnementRepository.findById(consultationDto.getAbonnement_id())
                .orElseThrow(() -> new RuntimeException("Abonnement not found"));
        Document document = documentRepository.findById(consultationDto.getDocument_id())
                .orElseThrow(() -> new RuntimeException("Document not found"));

        consultation.setAbonnement(abonnement);
        consultation.setDocument(document);

        // Sauvegarder l'entité
        Consultation savedConsultation = consultationRepository.save(consultation);

        // Convertir l'entité sauvegardée en DTO
        return convertToDto(savedConsultation);
    }


    @Override
    public ConsultationDto updateConsultation(Long id, ConsultationDto consultationDto) {
        // Récupérer la consultation existante
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation not found with id: " + id));

        // Mettre à jour les champs de base
        consultation.setDate_consult(consultationDto.getDate_consult());
        consultation.setDate_expiration(consultationDto.getDate_expiration());

        // Récupérer et assigner l'objet Abonnement correspondant à l'ID
        Abonnement abonnement = abonnementRepository.findById(consultationDto.getAbonnement_id())
                .orElseThrow(() -> new RuntimeException("Abonnement not found with id: " + consultationDto.getAbonnement_id()));
        consultation.setAbonnement(abonnement);

        // Récupérer et assigner l'objet Document correspondant à l'ID
        Document document = documentRepository.findById(consultationDto.getDocument_id())
                .orElseThrow(() -> new RuntimeException("Document not found with id: " + consultationDto.getDocument_id()));
        consultation.setDocument(document);

        // Sauvegarder la consultation mise à jour
        Consultation updatedConsultation = consultationRepository.save(consultation);

        // Convertir l'entité mise à jour en DTO et la retourner
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
        dto.setAbonnement_id(consultation.getAbonnement().getAbonnementId());
        dto.setDocument_id(consultation.getDocument().getDocument_id());
        return dto;
    }

    private Consultation convertToEntity(ConsultationDto dto, AbonnementRepository abonnementRepo, DocumentRepository documentRepo) {
        Consultation consultation = new Consultation();
        consultation.setConsult_id(dto.getConsult_id());
        consultation.setDate_consult(dto.getDate_consult());
        consultation.setDate_expiration(dto.getDate_expiration());

        // Récupérer l'objet Abonnement correspondant à l'ID
        Abonnement abonnement = abonnementRepo.findById(dto.getAbonnement_id())
                .orElseThrow(() -> new RuntimeException("Abonnement not found with id: " + dto.getAbonnement_id()));
        consultation.setAbonnement(abonnement);

        // Récupérer l'objet Document correspondant à l'ID
        Document document = documentRepo.findById(dto.getDocument_id())
                .orElseThrow(() -> new RuntimeException("Document not found with id: " + dto.getDocument_id()));
        consultation.setDocument(document);

        return consultation;
    }
}

