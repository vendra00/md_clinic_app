package com.gv.md_clinic_app.model.patient.allergy;

import jakarta.persistence.*;
import lombok.*;

/**
 * Allergy entity - abstract class
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "allergy_type")
public abstract class Allergy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String description;

}