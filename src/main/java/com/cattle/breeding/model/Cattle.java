package com.cattle.breeding.model;

import com.cattle.breeding.enums.CattleStatus;
import com.cattle.breeding.enums.Gendre;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "cattle")
public class Cattle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String rfid;

    @Column(nullable = false)
    private String breed;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gendre gender;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    private double weight;
    private String lineage;

    @Enumerated(EnumType.STRING)
    private CattleStatus status;

    @Column(name = "photo_url")
    private String photoUrl; // Path to uploaded photo

}
