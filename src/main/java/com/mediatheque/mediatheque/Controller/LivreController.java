package com.mediatheque.mediatheque.Controller;

import com.mediatheque.mediatheque.Dto.LivreDto;
import com.mediatheque.mediatheque.Service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livre")
public class LivreController {

    @Autowired
    private LivreService livreService;

    @PostMapping("/add")
    public ResponseEntity<String> addLivre(@RequestBody LivreDto livreDto) {
        if (livreDto == null) {
            return new ResponseEntity<>("LivreDto is null", HttpStatus.BAD_REQUEST);
        }

        String result = livreService.addLivre(livreDto);

        if (result.equals("Livre ajouté avec succès")) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<LivreDto>> getLivres() {
        List<LivreDto> allLivres = livreService.getLivres();

        if (allLivres.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(allLivres, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateLivre(@RequestBody LivreDto livreDto) {
        if (livreDto == null) {
            return new ResponseEntity<>("LivreDto is null", HttpStatus.BAD_REQUEST);
        }

        String result = livreService.updateLivre(livreDto);

        if (result.equals("Livre mis à jour avec succès")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<LivreDto> getLivreById(@PathVariable Long id) {
        try {
            LivreDto livreDto = livreService.getLivreById(id);
            return new ResponseEntity<>(livreDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLivre(@PathVariable Long id) {
        String result = livreService.deleteLivre(id);

        if (result.equals("Livre supprimé avec succès")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }


}
