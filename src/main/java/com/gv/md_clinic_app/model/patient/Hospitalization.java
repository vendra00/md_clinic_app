package com.gv.md_clinic_app.model.patient;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Represents the hospitalization of a patient - embedded in Patient
 */
@Data
@Embeddable
public class Hospitalization {

    @NotNull @NotEmpty
    private String reason;
    private String description;
    @NotNull @NotEmpty
    private String hospitalName;
    private String doctorName;
    @NotNull @NotEmpty
    private LocalDate hospitalizationDate;
    @NotNull @NotEmpty
    private LocalDate dischargeDate;

    @ElementCollection
    private List<Medication> medications;

    /**
     * Calculates the number of days between the hospitalization date and the discharge date
     * @return the number of days between the hospitalization date and the discharge date
     */
    public long getDaysOfHospitalization() {
        return ChronoUnit.DAYS.between(hospitalizationDate, dischargeDate);
    }
}
