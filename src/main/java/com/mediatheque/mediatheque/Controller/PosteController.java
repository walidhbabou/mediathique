package com.mediatheque.mediatheque.Controller;

import com.mediatheque.mediatheque.Dto.PosteDto;
import com.mediatheque.mediatheque.Service.PosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/postes")
public class PosteController {

    @Autowired
    private PosteService posteService;

    @GetMapping("/libres")
    public ResponseEntity<List<PosteDto>> getPostesLibres() {
        return ResponseEntity.ok(posteService.getPostesLibres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PosteDto> getPosteById(@PathVariable Integer id) {
        return ResponseEntity.ok(posteService.getPosteById(id));
    }

    @PostMapping
    public ResponseEntity<PosteDto> savePoste(@RequestBody PosteDto posteDto) {
        return ResponseEntity.ok(posteService.savePoste(posteDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PosteDto> updatePoste(@PathVariable Integer id, @RequestBody PosteDto posteDto) {
        return ResponseEntity.ok(posteService.updatePoste(id, posteDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoste(@PathVariable Integer id) {
        posteService.deletePoste(id);
        return ResponseEntity.noContent().build();
    }
}
