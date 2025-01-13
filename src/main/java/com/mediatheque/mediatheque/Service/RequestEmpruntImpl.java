package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.RequestEmpruntDto;
import com.mediatheque.mediatheque.Entity.*;
import com.mediatheque.mediatheque.Repository.*;
import com.mediatheque.mediatheque.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestEmpruntImpl implements RequestEmpruntService {

    @Autowired
    private RequestEmpruntRepository requestEmpruntRepository;

    @Autowired
    private LecteurRepository lecteurRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public RequestEmpruntDto createRequest(RequestEmpruntDto requestEmpruntDto) {
        // Vérifier si le lecteur et le document existent
        Lecteur lecteur = lecteurRepository.findById(requestEmpruntDto.getLecteurId())
                .orElseThrow(() -> new RuntimeException("Lecteur not found"));
        Document document = documentRepository.findById(requestEmpruntDto.getDocumentId())
                .orElseThrow(() -> new RuntimeException("Document not found"));

        // Créer une nouvelle demande d'emprunt
        RequestEmprunt requestEmprunt = new RequestEmprunt();
        requestEmprunt.setDateRequest(new Date());
        requestEmprunt.setStatus(Status.EN_ATTENTE); // Par défaut, la demande est en attente
        requestEmprunt.setLecteur(lecteur);
        requestEmprunt.setDocument(document);

        // Sauvegarder la demande
        RequestEmprunt savedRequest = requestEmpruntRepository.save(requestEmprunt);

        // Convertir l'entité en DTO
        return convertToDto(savedRequest);
    }

    @Override
    public List<RequestEmpruntDto> getAllRequests() {
        return requestEmpruntRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RequestEmpruntDto getRequestById(Long id) {
        RequestEmprunt request = requestEmpruntRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        return convertToDto(request);
    }

    @Override
    public RequestEmpruntDto updateRequestStatus(Long id, String status) {
        RequestEmprunt request = requestEmpruntRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        // Mettre à jour le statut de la demande
        request.setStatus(Status.valueOf(status));
        RequestEmprunt updatedRequest = requestEmpruntRepository.save(request);

        return convertToDto(updatedRequest);
    }

    @Override
    public void deleteRequest(Long id) {
        requestEmpruntRepository.deleteById(id);
    }

    private RequestEmpruntDto convertToDto(RequestEmprunt requestEmprunt) {
        RequestEmpruntDto dto = new RequestEmpruntDto();
        dto.setRequestId(requestEmprunt.getRequestId());
        dto.setDateRequest(requestEmprunt.getDateRequest());
        dto.setStatus(requestEmprunt.getStatus());
        dto.setLecteurId(requestEmprunt.getLecteur().getLecteurId());
        dto.setDocumentId(requestEmprunt.getDocument().getDocument_id());
        return dto;
    }
}