package com.mediatheque.mediatheque.Controller;
import com.mediatheque.mediatheque.Dto.JournaleDto;
import com.mediatheque.mediatheque.Dto.LivreDto;
import com.mediatheque.mediatheque.Service.JournaleService;
import com.mediatheque.mediatheque.Service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Journal")
public class JournaleController {
    @Autowired
    private JournaleService journaleService;
    @PostMapping("/add")
    public ResponseEntity<String> addJournale(@RequestBody JournaleDto journaleDto) {
        if (journaleDto == null) {
            return new ResponseEntity<>("JournaleDto is null", HttpStatus.BAD_REQUEST);
        }
        String response = journaleService.addJournale(journaleDto);

        if (response.equals("Journal ajouté avec succès")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<JournaleDto>> getJournales() {
        List<JournaleDto> journales = journaleService.getJournales();

        if (journales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(journales, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateJournale(@RequestBody JournaleDto journaleDto) {
        if (journaleDto == null) {
            return new ResponseEntity<>("Journaele is null", HttpStatus.BAD_REQUEST);
        }

        String response = journaleService.updateJournale(journaleDto);

        if (response.equals("Livre mis à jour avec succès")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }}
