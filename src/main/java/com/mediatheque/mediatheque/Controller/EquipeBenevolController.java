package com.mediatheque.mediatheque.Controller;

import com.mediatheque.mediatheque.Dto.EquipeBenevolDto;
import com.mediatheque.mediatheque.Service.EquipeBenevolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipe-benevol")
public class EquipeBenevolController {

    @Autowired
    private EquipeBenevolService equipeBenevolService;

    @PostMapping("/add")
    public ResponseEntity<EquipeBenevolDto> addBenevol(@RequestBody EquipeBenevolDto equipeBenevolDto) {
        EquipeBenevolDto result = equipeBenevolService.addBenevol(equipeBenevolDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{benevolId}")
    public ResponseEntity<EquipeBenevolDto> updateBenevol(@PathVariable Long benevolId, @RequestBody EquipeBenevolDto equipeBenevolDto) {
        Optional<EquipeBenevolDto> result = equipeBenevolService.updateBenevol(benevolId, equipeBenevolDto);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{benevolId}")
    public ResponseEntity<Void> deleteBenevol(@PathVariable Long benevolId) {
        boolean isDeleted = equipeBenevolService.deleteBenevol(benevolId);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public List<EquipeBenevolDto> getAllBenevols() {
        return equipeBenevolService.getAllBenevols();
    }

    @GetMapping("/{benevolId}")
    public ResponseEntity<EquipeBenevolDto> getBenevolById(@PathVariable Long benevolId) {
        Optional<EquipeBenevolDto> result = equipeBenevolService.getBenevolById(benevolId);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
