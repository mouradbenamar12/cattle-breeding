package com.cattle.breeding.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "vaccinations")
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cattle_id", nullable = false)
    private Cattle cattle;

    private String vaccineName;
    private LocalDate vaccinationDate;
    private LocalDate nextVaccinationDate; // For reminders
}