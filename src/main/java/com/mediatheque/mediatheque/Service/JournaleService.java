package com.mediatheque.mediatheque.Service;
import com.mediatheque.mediatheque.Dto.JournaleDto;


import java.util.List;
public interface JournaleService {
    public String addJournale(JournaleDto JournaleDto);
    public List<JournaleDto> getJournales();
    public String updateJournale(JournaleDto JournaleDto);
    JournaleDto getJournaleById(Long id); // Nouvelle méthode pour récupérer un journal par ID
    String deleteJournale(Long id);

}

