package com.gv.md_clinic_app.model.dto;

import com.gv.md_clinic_app.model.enums.BloodType;
import com.gv.md_clinic_app.model.enums.Choice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email; // Might be omitted for certain roles
    private String phone; // Might be omitted for certain roles
    private AddressDto address;
    private MedicalHistoryDto medicalHistory; // Detailed for doctors, summary or omitted for others
    private Set<ConditionDto> conditions; // Detailed for doctors and admin, summary or omitted for patients
    private BloodType bloodType;
    private Choice isOrganDonor;
    private LocalDate dob;
    private int age; // Age can be calculated from dob

    // Depending on your requirements, you might want to include methods to hide certain sensitive information
    public void hideSensitiveDataForPatients() {
        this.medicalHistory = null;
        this.conditions = null;
    }

    public void hideSensitiveDataForAdmins() {
        this.medicalHistory = null;
        this.phone = null;
        this.address = null;
    }
}