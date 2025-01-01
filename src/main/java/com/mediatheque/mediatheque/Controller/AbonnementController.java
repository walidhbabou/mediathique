package com.mediatheque.mediatheque.Controller;

import com.mediatheque.mediatheque.Dto.AbonnementDto;
import com.mediatheque.mediatheque.Service.AbonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Mediatheque/abo")
public class AbonnementController {

    @Autowired
    private AbonnementService abonnementService;

    @PostMapping("/add")
    public ResponseEntity<AbonnementDto> createAbonnement(@RequestBody AbonnementDto abonnementDto) {
        AbonnementDto createdAbonnement = abonnementService.createAbonnement(abonnementDto);
        return new ResponseEntity<>(createdAbonnement, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AbonnementDto> updateAbonnement(@PathVariable Long id, @RequestBody AbonnementDto abonnementDto) {
        AbonnementDto updatedAbonnement = abonnementService.updateAbonnement(id, abonnementDto);
        return new ResponseEntity<>(updatedAbonnement, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbonnement(@PathVariable Long id) {
        abonnementService.deleteAbonnement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbonnementDto> getAbonnementById(@PathVariable Long id) {
        AbonnementDto abonnement = abonnementService.getAbonnementById(id);
        return new ResponseEntity<>(abonnement, HttpStatus.OK);
    }

    @GetMapping("/allAbonnement")
    public ResponseEntity<List<AbonnementDto>> getAllAbonnements() {
        List<AbonnementDto> abonnements = abonnementService.getAllAbonnements();
        return new ResponseEntity<>(abonnements, HttpStatus.OK);
    }
}
