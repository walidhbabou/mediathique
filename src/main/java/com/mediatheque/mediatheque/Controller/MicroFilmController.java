package com.mediatheque.mediatheque.Controller;

import com.mediatheque.mediatheque.Dto.MicroFilmDto;
import com.mediatheque.mediatheque.Service.MicroFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/microfilms")
public class MicroFilmController {

    @Autowired
    private MicroFilmService microFilmService;

    @GetMapping
    public ResponseEntity<List<MicroFilmDto>> getAllMicroFilms() {
        return ResponseEntity.ok(microFilmService.getAllMicroFilms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MicroFilmDto>> getMicroFilmById(@PathVariable Long id) {
        return ResponseEntity.ok(microFilmService.getMicroFilmById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<MicroFilmDto> createMicroFilm(@RequestBody MicroFilmDto microFilmDto) {
        return ResponseEntity.ok(microFilmService.createMicroFilm(microFilmDto));
    }

    @DeleteMapping("/supp/{id}")
    public ResponseEntity<Void> deleteMicroFilm(@PathVariable Long id) {
        microFilmService.deleteMicroFilm(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<String> updateMicroFilm(@PathVariable Long id, @RequestBody MicroFilmDto microFilmDto) {
        return ResponseEntity.ok(microFilmService.updateMicroFilm(id, microFilmDto));
    }
}
