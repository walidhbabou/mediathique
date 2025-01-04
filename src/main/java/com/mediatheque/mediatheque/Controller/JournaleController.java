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
            return new ResponseEntity<>("Le journale est null", HttpStatus.BAD_REQUEST);
        }

        // Appeler le service pour mettre à jour le journal
        String response = journaleService.updateJournale(journaleDto);

        // Vérifiez le résultat de la mise à jour
        if (response.equals("Journal mis à jour avec succès.")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<JournaleDto> getJournaleById(@PathVariable Long id) {
        try {
            JournaleDto journaleDto = journaleService.getJournaleById(id);
            return new ResponseEntity<>(journaleDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJournale(@PathVariable Long id) {
        try {
            String response = journaleService.deleteJournale(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }}
}
