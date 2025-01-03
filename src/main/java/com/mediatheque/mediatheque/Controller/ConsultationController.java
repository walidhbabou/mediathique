package com.mediatheque.mediatheque.Controller;

import com.mediatheque.mediatheque.Dto.ConsultationDto;
import com.mediatheque.mediatheque.Service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @GetMapping
    public ResponseEntity<List<ConsultationDto>> getAllConsultations() {
        return ResponseEntity.ok(consultationService.getAllConsultations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDto> getConsultationById(@PathVariable Long id) {
        return ResponseEntity.ok(consultationService.getConsultationById(id));
    }

    @GetMapping("/abonnement/{abonnementId}")
    public ResponseEntity<List<ConsultationDto>> getConsultationsByAbonnement(@PathVariable Long abonnementId) {
        return ResponseEntity.ok(consultationService.getConsultationsByAbonnement(abonnementId));
    }

    @GetMapping("/document/{documentId}")
    public ResponseEntity<List<ConsultationDto>> getConsultationsByDocument(@PathVariable Long documentId) {
        return ResponseEntity.ok(consultationService.getConsultationsByDocument(documentId));
    }

    @PostMapping
    public ResponseEntity<ConsultationDto> saveConsultation(@RequestBody ConsultationDto consultationDto) {
        return ResponseEntity.ok(consultationService.saveConsultation(consultationDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultationDto> updateConsultation(@PathVariable Long id, @RequestBody ConsultationDto consultationDto) {
        return ResponseEntity.ok(consultationService.updateConsultation(id, consultationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        consultationService.deleteConsultation(id);
        return ResponseEntity.noContent().build();
    }
}
