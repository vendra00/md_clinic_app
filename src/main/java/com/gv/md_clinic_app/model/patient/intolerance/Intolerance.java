package com.gv.md_clinic_app.model.patient.intolerance;

import jakarta.persistence.*;
import lombok.*;

/**
 * Intolerance entity - embedded in Patient
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "intolerance_type")
public abstract class Intolerance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String description;
}
