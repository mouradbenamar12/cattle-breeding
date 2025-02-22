package com.cattle.breeding.service;

import com.cattle.breeding.exception.CattleNotFoundException;
import com.cattle.breeding.model.Cattle;
import com.cattle.breeding.model.Vaccination;
import com.cattle.breeding.repository.CattleRepository;
import com.cattle.breeding.repository.VaccinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VaccinationService {
    private final VaccinationRepository vaccinationRepository;
    private final CattleRepository cattleRepository;

    public Vaccination createVaccination(UUID cattleId, Vaccination vaccination) {
        Cattle cattle = cattleRepository.findById(cattleId)
                .orElseThrow(() -> new CattleNotFoundException("Cattle not found with ID: " + cattleId));
        vaccination.setCattle(cattle);
        return vaccinationRepository.save(vaccination);
    }

    public List<Vaccination> getVaccinationsByCattleId(UUID cattleId) {
        return vaccinationRepository.findByCattleId(cattleId);
    }

    public List<Vaccination> getUpcomingVaccinations(LocalDate date) {
        return vaccinationRepository.findByNextVaccinationDate(date);
    }
}