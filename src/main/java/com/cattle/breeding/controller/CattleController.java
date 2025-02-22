package com.cattle.breeding.controller;

import com.cattle.breeding.exception.CattleNotFoundException;
import com.cattle.breeding.model.Cattle;
import com.cattle.breeding.service.CattleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cattles")
@RequiredArgsConstructor
public class CattleController {
    private final CattleService cattleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cattle createCattle(@RequestBody Cattle cattle) {
        return cattleService.createCattle(cattle);
    }

    @PutMapping("/{id}")
    public Cattle updateCattle(@PathVariable UUID id, @RequestBody Cattle cattle) {
        return cattleService.updateCattle(id, cattle);
    }

    @GetMapping
    public List<Cattle> getAllCattle() {
        return cattleService.getAllCattle();
    }

    @GetMapping("/{id}")
    public Cattle getCattleById(@PathVariable UUID id) {
        return cattleService.getCattleById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteCattle(@PathVariable UUID id) {
        try {
            String message = cattleService.deleteCattle(id);
            return ResponseEntity.ok(message);
        } catch (CattleNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/{id}/upload-photo")
    public Cattle uploadPhoto(
            @PathVariable UUID id,
            @RequestParam("photo") MultipartFile photo
    ) throws IOException {
        Cattle cattle = cattleService.getCattleById(id);
        String uploadDir = "uploads/";
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = id + "_" + photo.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(photo.getInputStream(), filePath);

        cattle.setPhotoUrl(filePath.toString());
        return cattleService.updateCattle(id, cattle);
    }
}