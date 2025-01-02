package com.mediatheque.mediatheque.Controller;

import com.mediatheque.mediatheque.Dto.MicroFilmDto;
import com.mediatheque.mediatheque.Service.MicroFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Mediatheque/MicroFilm")
public class MicroFilmController {

    @Autowired
    private MicroFilmService microFilmService;

    // Endpoint pour ajouter un microfilm
    @PostMapping(path = "/add")
    public ResponseEntity<String> addMicroFilm(@RequestBody MicroFilmDto microFilmDto) {
        if (microFilmDto == null) {
            return new ResponseEntity<>("MicroFilmDto is null", HttpStatus.BAD_REQUEST);
        }
        String result = microFilmService.addMicroFilm(microFilmDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // Endpoint pour récupérer tous les microfilms
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<MicroFilmDto>> getAllMicroFilms() {
        List<MicroFilmDto> microFilms = microFilmService.getAllMicroFilms();
        if (microFilms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(microFilms, HttpStatus.OK);
    }

    // Endpoint pour récupérer un microfilm par ID
    @GetMapping(path = "/get/{microId}")
    public ResponseEntity<MicroFilmDto> getMicroFilmById(@PathVariable Long microId) {
        MicroFilmDto microFilm = microFilmService.getMicroFilmById(microId);
        if (microFilm == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(microFilm, HttpStatus.OK);
    }

    // Endpoint pour mettre à jour un microfilm
    @PutMapping(path = "/update")
    public ResponseEntity<String> updateMicroFilm(@RequestBody MicroFilmDto microFilmDto) {
        if (microFilmDto == null) {
            return new ResponseEntity<>("MicroFilmDto is null", HttpStatus.BAD_REQUEST);
        }
        String result = microFilmService.updateMicroFilm(microFilmDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Endpoint pour supprimer un microfilm
    @DeleteMapping(path = "/delete/{microId}")
    public ResponseEntity<String> deleteMicroFilm(@PathVariable Long microId) {
        String result = microFilmService.deleteMicroFilm(microId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Endpoint pour rechercher des microfilms par document ID
    @GetMapping(path = "/byDocument/{documentId}")
    public ResponseEntity<List<MicroFilmDto>> getMicroFilmsByDocumentId(@PathVariable Long documentId) {
        List<MicroFilmDto> microFilms = microFilmService.getMicroFilmsByDocumentId(documentId);
        if (microFilms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(microFilms, HttpStatus.OK);
    }
}
