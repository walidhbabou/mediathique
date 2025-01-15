package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.EmpruntDto;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void sendNotification(EmpruntDto empruntDto) {
        // Logique pour envoyer une notification (email, SMS, etc.)
        System.out.println("Notification envoyée pour l'emprunt ID: " + empruntDto.getEmprunt_id());
    }
}
