package com.mediatheque.mediatheque.Controller;

import com.mediatheque.mediatheque.Dto.EmpruntDto;
import com.mediatheque.mediatheque.Service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {

    @Autowired
    private EmpruntService empruntService;

    @PostMapping("/add")
    public ResponseEntity<EmpruntDto> createEmprunt(@RequestBody EmpruntDto empruntDto) {
        return ResponseEntity.ok(empruntService.createEmprunt(empruntDto));
    }

    @GetMapping
    public ResponseEntity<List<EmpruntDto>> getAllEmprunts() {
        return ResponseEntity.ok(empruntService.getAllEmprunts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<EmpruntDto>> getEmpruntById(@PathVariable Long id) {
        return ResponseEntity.ok(empruntService.getEmpruntById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmpruntDto> updateEmprunt(@PathVariable Long id, @RequestBody EmpruntDto empruntDto) {
        return ResponseEntity.ok(empruntService.updateEmprunt(id, empruntDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmprunt(@PathVariable Long id) {
        empruntService.deleteEmprunt(id);
        return ResponseEntity.noContent().build();
    }
}