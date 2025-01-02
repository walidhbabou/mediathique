package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.MicroFilmDto;
import com.mediatheque.mediatheque.Entity.MicroFilm;
import com.mediatheque.mediatheque.Repository.MicroFilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MicroFilmImp implements MicroFilmService {
    @Autowired
    private MicroFilmRepository microFilmRepository;

    @Override
    public String addMicroFilm(MicroFilmDto microFilmDto) {
        MicroFilm microFilm = new MicroFilm();
        microFilm.setDocument(microFilmDto.getDocument());
        microFilmRepository.save(microFilm);
        return "Microfilm ajouté avec succès";
    }

    @Override
    public List<MicroFilmDto> getAllMicroFilms() {
        List<MicroFilm> microFilms = microFilmRepository.findAll();
        return microFilms.stream()
                .map(microFilm -> new MicroFilmDto(microFilm.getMicro_id(), microFilm.getDocument()))
                .collect(Collectors.toList());
    }

    @Override
    public MicroFilmDto getMicroFilmById(Long microId) {
        Optional<MicroFilm> microFilmOptional = microFilmRepository.findById(microId);
        return microFilmOptional.map(microFilm -> new MicroFilmDto(microFilm.getMicro_id(), microFilm.getDocument()))
                .orElse(null);
    }

    @Override
    public String updateMicroFilm(MicroFilmDto microFilmDto) {
        Optional<MicroFilm> existingMicroFilm = microFilmRepository.findById(microFilmDto.getMicro_id());
        if (existingMicroFilm.isPresent()) {
            MicroFilm microFilm = existingMicroFilm.get();
            microFilm.setDocument(microFilmDto.getDocument());
            microFilmRepository.save(microFilm);
            return "Microfilm mis à jour avec succès";
        } else {
            return "Microfilm introuvable";
        }
    }

    @Override
    public String deleteMicroFilm(Long microId) {
        if (microFilmRepository.existsById(microId)) {
            microFilmRepository.deleteById(microId);
            return "Microfilm supprimé avec succès";
        } else {
            return "Microfilm introuvable";
        }
    }

    @Override
    public List<MicroFilmDto> getMicroFilmsByDocumentId(Long documentId) {
        List<MicroFilm> microFilms = microFilmRepository.findByDocument_Id(documentId); // Correction ici
        return microFilms.stream()
                .map(microFilm -> new MicroFilmDto(microFilm.getMicro_id(), microFilm.getDocument()))
                .collect(Collectors.toList());
    }
}
