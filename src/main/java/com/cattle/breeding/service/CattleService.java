package com.cattle.breeding.service;

import com.cattle.breeding.exception.CattleNotFoundException;
import com.cattle.breeding.model.Cattle;
import com.cattle.breeding.repository.CattleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CattleService {

    private static final Logger logger = LoggerFactory.getLogger(CattleService.class);
    private final CattleRepository cattleRepository;

    public Cattle createCattle(Cattle cattle) {
        return cattleRepository.save(cattle);
    }

    public Cattle updateCattle(UUID id, Cattle updatedCattle) {
        Cattle existingCattle = cattleRepository.findById(id)
                .orElseThrow(() -> new CattleNotFoundException("Cattle not found with ID: " + id));

        existingCattle.setBreed(updatedCattle.getBreed());
        existingCattle.setWeight(updatedCattle.getWeight());
        existingCattle.setStatus(updatedCattle.getStatus());
        // Update other fields as needed

        return cattleRepository.save(existingCattle);
    }

    public String deleteCattle(UUID id) {
        // Check if cattle exists
        Optional<Cattle> cattleOptional = cattleRepository.findById(id);
        if (cattleOptional.isEmpty()) {
            logger.warn("Cattle not found with ID: {}", id);
            throw new CattleNotFoundException("Cattle not found with ID: " + id);
        }

        // Delete the cattle
        cattleRepository.deleteById(id);
        logger.info("Cattle with ID {} is successfully deleted.", id);
        return "Cattle with ID " + id + " is successfully deleted.";
    }

    public List<Cattle> getAllCattle() {
        return cattleRepository.findAll();
    }

    public Cattle getCattleById(UUID id) {
        return cattleRepository.findById(id)
                .orElseThrow(() -> new CattleNotFoundException("Cattle not found with ID: " + id));
    }
}