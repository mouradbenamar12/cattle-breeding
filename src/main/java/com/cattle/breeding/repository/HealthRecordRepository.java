package com.cattle.breeding.repository;

import com.cattle.breeding.model.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface HealthRecordRepository extends JpaRepository<HealthRecord, UUID> {
    List<HealthRecord> findByCattleId(UUID cattleId);
}