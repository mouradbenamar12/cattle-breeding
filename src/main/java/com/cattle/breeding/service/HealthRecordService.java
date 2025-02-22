package com.cattle.breeding.service;

import com.cattle.breeding.exception.CattleNotFoundException;
import com.cattle.breeding.model.Cattle;
import com.cattle.breeding.model.HealthRecord;
import com.cattle.breeding.repository.CattleRepository;
import com.cattle.breeding.repository.HealthRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HealthRecordService {
    private final HealthRecordRepository healthRecordRepository;
    private final CattleRepository cattleRepository;

    public HealthRecord createHealthRecord(UUID cattleId, HealthRecord healthRecord) {
        Cattle cattle = cattleRepository.findById(cattleId)
                .orElseThrow(() -> new CattleNotFoundException("Cattle not found with ID: " + cattleId));
        healthRecord.setCattle(cattle);
        return healthRecordRepository.save(healthRecord);
    }

    public List<HealthRecord> getHealthRecordsByCattleId(UUID cattleId) {
        return healthRecordRepository.findByCattleId(cattleId);
    }

    public String generateHealthReport(UUID cattleId) {
        List<HealthRecord> healthRecords = healthRecordRepository.findByCattleId(cattleId);
        StringBuilder report = new StringBuilder();
        report.append("Health Report for Cattle ID: ").append(cattleId).append("\n");

        for (HealthRecord record : healthRecords) {
            report.append("Date: ").append(record.getVisitDate())
                    .append(", Issue: ").append(record.getHealthIssue())
                    .append(", Treatment: ").append(record.getTreatment())
                    .append(", Vet: ").append(record.getVeterinarianName())
                    .append("\n");
        }

        return report.toString();
    }
}