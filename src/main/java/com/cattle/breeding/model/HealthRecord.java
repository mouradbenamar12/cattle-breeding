package com.cattle.breeding.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "health_records")
public class HealthRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cattle_id", nullable = false)
    private Cattle cattle;

    private String healthIssue;
    private String treatment;
    private LocalDate visitDate;
    private String veterinarianName;
}