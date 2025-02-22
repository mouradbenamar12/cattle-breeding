package com.cattle.breeding.repository;

import com.cattle.breeding.model.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface VaccinationRepository extends JpaRepository<Vaccination, UUID> {
    List<Vaccination> findByCattleId(UUID cattleId);
    List<Vaccination> findByNextVaccinationDate(LocalDate date); // For reminders
}