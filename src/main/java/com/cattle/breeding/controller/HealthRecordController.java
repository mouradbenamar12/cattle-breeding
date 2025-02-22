package com.cattle.breeding.controller;

import com.cattle.breeding.model.HealthRecord;
import com.cattle.breeding.service.HealthRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cattles/{cattleId}/health-records")
@RequiredArgsConstructor
public class HealthRecordController {
    private final HealthRecordService healthRecordService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HealthRecord createHealthRecord(
            @PathVariable UUID cattleId,
            @RequestBody HealthRecord healthRecord
    ) {
        return healthRecordService.createHealthRecord(cattleId, healthRecord);
    }

    @GetMapping
    public List<HealthRecord> getHealthRecords(@PathVariable UUID cattleId) {
        return healthRecordService.getHealthRecordsByCattleId(cattleId);
    }

    @GetMapping("/report")
    public String getHealthReport(@PathVariable UUID cattleId) {
        return healthRecordService.generateHealthReport(cattleId);
    }
}