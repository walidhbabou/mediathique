package com.mediatheque.mediatheque.Controller;

import com.mediatheque.mediatheque.Dto.AchatDto;
import com.mediatheque.mediatheque.Service.AchatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/achats")
public class AchatController {

    @Autowired
    private AchatService achatService;

    @GetMapping
    public ResponseEntity<List<AchatDto>> getAllAchats() {
        return ResponseEntity.ok(achatService.getAllAchats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AchatDto> getAchatById(@PathVariable Long id) {
        return ResponseEntity.ok(achatService.getAchatById(id));
    }

    @PostMapping
    public ResponseEntity<AchatDto> saveAchat(@RequestBody AchatDto achatDto) {
        return ResponseEntity.ok(achatService.saveAchat(achatDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AchatDto> updateAchat(@PathVariable Long id, @RequestBody AchatDto achatDto) {
        return ResponseEntity.ok(achatService.updateAchat(id, achatDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAchat(@PathVariable Long id) {
        achatService.deleteAchat(id);
        return ResponseEntity.noContent().build();
    }
}
