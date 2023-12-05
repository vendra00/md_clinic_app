package com.gv.md_clinic_app.model.patient;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Condition entity
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "conditions")
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull @NotEmpty
    private String name;
    private String description;

}
