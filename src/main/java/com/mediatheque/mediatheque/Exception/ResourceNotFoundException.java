package com.mediatheque.mediatheque.Exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message); // Appelle le constructeur de RuntimeException avec le message d'erreur
    }
}