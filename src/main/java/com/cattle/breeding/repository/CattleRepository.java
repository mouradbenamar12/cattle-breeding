package com.cattle.breeding.repository;

import com.cattle.breeding.model.Cattle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CattleRepository extends JpaRepository<Cattle, UUID> {
    Optional<Cattle> findByRfid(String rfid);
}