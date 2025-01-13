package com.mediatheque.mediatheque.Controller;

import com.mediatheque.mediatheque.Dto.RequestEmpruntDto;
import com.mediatheque.mediatheque.Service.RequestEmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestEmpruntController {

    @Autowired
    private RequestEmpruntService requestEmpruntService;

    @PostMapping("/add")
    public ResponseEntity<RequestEmpruntDto> createRequest(@RequestBody RequestEmpruntDto requestEmpruntDto) {
        return ResponseEntity.ok(requestEmpruntService.createRequest(requestEmpruntDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RequestEmpruntDto>> getAllRequests() {
        return ResponseEntity.ok(requestEmpruntService.getAllRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestEmpruntDto> getRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(requestEmpruntService.getRequestById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RequestEmpruntDto> updateRequestStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(requestEmpruntService.updateRequestStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        requestEmpruntService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}