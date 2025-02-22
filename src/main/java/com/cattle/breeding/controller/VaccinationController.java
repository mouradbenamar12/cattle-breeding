package com.cattle.breeding.controller;

import com.cattle.breeding.model.Vaccination;
import com.cattle.breeding.service.VaccinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cattles/{cattleId}/vaccinations")
@RequiredArgsConstructor
public class VaccinationController {
    private final VaccinationService vaccinationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vaccination createVaccination(
            @PathVariable UUID cattleId,
            @RequestBody Vaccination vaccination
    ) {
        return vaccinationService.createVaccination(cattleId, vaccination);
    }

    @GetMapping
    public List<Vaccination> getVaccinations(@PathVariable UUID cattleId) {
        return vaccinationService.getVaccinationsByCattleId(cattleId);
    }

    @GetMapping("/upcoming")
    public List<Vaccination> getUpcomingVaccinations(@RequestParam LocalDate date) {
        return vaccinationService.getUpcomingVaccinations(date);
    }
}