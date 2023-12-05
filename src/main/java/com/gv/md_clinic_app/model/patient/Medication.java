package com.gv.md_clinic_app.model.patient;

import com.gv.md_clinic_app.model.enums.Dosage;
import com.gv.md_clinic_app.model.enums.Frequency;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * Represents the medication of a patient - embedded in Patient
 */
@Data
@Embeddable
public class Medication {

    @NotNull @NotEmpty
    private String medicationName;
    private String description;
    @NotNull @NotEmpty
    private Dosage dosage;
    private String dosageQuantity;
    @NotNull @NotEmpty
    private Frequency frequency;
    private String frequencyQuantity;
    @NotNull @NotEmpty
    private LocalDate duration;
    private String reason;

}
