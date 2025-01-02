package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.MicroFilmDto;
import com.mediatheque.mediatheque.Entity.Document;
import com.mediatheque.mediatheque.Entity.MicroFilm;
import com.mediatheque.mediatheque.Repository.DocumentRepository;
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
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public List<MicroFilmDto> getAllMicroFilms() {
        List<MicroFilm> microFilms = microFilmRepository.findAll();
        return microFilms.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MicroFilmDto> getMicroFilmById(Long id) {
        Optional<MicroFilm> microFilm = microFilmRepository.findById(id);
        return microFilm.map(this::convertEntityToDto);
    }

    @Override
    public MicroFilmDto createMicroFilm(MicroFilmDto microFilmDto) {
        if (microFilmDto.getDocument() == null) {
            throw new IllegalArgumentException("Document cannot be null");
        }

        Document document = documentRepository.save(microFilmDto.getDocument());

        MicroFilm microFilm = new MicroFilm();
        microFilm.setDocument(document);

        microFilm = microFilmRepository.save(microFilm);

        MicroFilmDto responseDto = new MicroFilmDto();
        responseDto.setMicro_id(microFilm.getMicro_id());
        responseDto.setDocument(microFilm.getDocument());

        return responseDto;
    }

    @Override
    public void deleteMicroFilm(Long id) {
        Optional<MicroFilm> microFilmOptional = microFilmRepository.findById(id);
        if (microFilmOptional.isPresent()) {
            MicroFilm microFilm = microFilmOptional.get();
            microFilmRepository.deleteById(id);
            documentRepository.deleteById(microFilm.getDocument().getDocument_id());
        } else {
            throw new IllegalArgumentException("MicroFilm with id " + id + " not found");
        }
    }


    @Override
    public String updateMicroFilm(Long microFilmId, MicroFilmDto microFilmDto) {
        Optional<MicroFilm> existingMicroFilm = microFilmRepository.findById(microFilmId);

        if (existingMicroFilm.isPresent()) {
            MicroFilm microFilm = existingMicroFilm.get();
            Document updatedDocument = documentRepository.save(microFilmDto.getDocument());
            microFilm.setDocument(updatedDocument);
            microFilmRepository.save(microFilm);
            return "MicroFilm updated successfully";
        } else {
            throw new IllegalArgumentException("MicroFilm with id " + microFilmId + " not found");
        }
    }

    private MicroFilmDto convertEntityToDto(MicroFilm microFilm) {
        return new MicroFilmDto(microFilm.getMicro_id(), microFilm.getDocument());
    }

    private MicroFilm convertDtoToEntity(MicroFilmDto microFilmDto) {
        return new MicroFilm(microFilmDto.getMicro_id(), microFilmDto.getDocument());
    }
}
