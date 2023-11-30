package com.gv.md_clinic_app.model.dto.patient;

import com.gv.md_clinic_app.model.patient.Medication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents the hospitalization of a patient
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HospitalizationDto {
    private String reason;
    private String description;
    private String hospitalName;
    private String doctorName;
    private LocalDate hospitalizationDate;
    private LocalDate dischargeDate;
    private List<Medication> medications;
    private long daysOfHospitalization;
}
