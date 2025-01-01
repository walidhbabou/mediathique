package com.mediatheque.mediatheque.Controller;
import com.mediatheque.mediatheque.Dto.CdRomDto;
import com.mediatheque.mediatheque.Service.CdRomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("Mediatheque/CdRom")
public class cdRomController {
    @Autowired
    private CdRomService cdRomService;
    // Endpoint pour ajouter un CD-ROM
    @PostMapping(path = "/add")
    public ResponseEntity<String> addCdRom(@RequestBody CdRomDto cdRomDto) {
        if (cdRomDto == null) {
            return new ResponseEntity<>("CdRomDto is null", HttpStatus.BAD_REQUEST);
        }

        String result = cdRomService.addCdRom(cdRomDto);
        if (result.equals("CD-ROM added successfully")) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint pour récupérer tous les CD-ROMs
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<CdRomDto>> getAllCdRoms() {
        List<CdRomDto> cdRoms = cdRomService.getAllCdRoms();
        if (cdRoms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cdRoms, HttpStatus.OK);
    }

    // Endpoint pour mettre à jour un CD-ROM
    @PutMapping(path = "/update")
    public ResponseEntity<String> updateCdRom(@RequestBody CdRomDto cdRomDto) {
        if (cdRomDto == null) {
            return new ResponseEntity<>("CdRomDto is null", HttpStatus.BAD_REQUEST);
        }

        String result = cdRomService.updateCdRom(cdRomDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Endpoint pour supprimer un CD-ROM
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteCdRom(@PathVariable Long id) {
        String result = cdRomService.deleteCdRom(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}





