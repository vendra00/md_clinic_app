package com.gv.md_clinic_app.model.dto.patient;

import com.gv.md_clinic_app.model.dto.*;
import com.gv.md_clinic_app.model.enums.BloodType;
import com.gv.md_clinic_app.model.enums.Choice;
import com.gv.md_clinic_app.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

/**
 * Represents a patient
 */
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
    private HabitDto habit;
    private MedicalHistoryDto medicalHistory; // Detailed for doctors, summary or omitted for others
    private EmergencyContactDto emergencyContact; // Detailed for doctors, summary or omitted for others
    private Set<ConditionDto> conditions; // Detailed for doctors and admin, summary or omitted for patients
    private String historyId;
    private double weight;
    private double height;
    private BloodType bloodType;
    private Choice isOrganDonor;
    private Gender gender;
    private LocalDate dob;
    private int age; // Age can be calculated from dob
    private double imc; // IMC can be calculated from weight and height

    /**
     * Hides sensitive data for patients
     */
    public void hideSensitiveDataForPatients() {
        this.medicalHistory = null;
        this.conditions = null;
        this.historyId = null;
    }

    /**
     * Hides sensitive data for doctors
     */
    public void hideSensitiveDataForAdmins() {
        this.medicalHistory = null;
        this.phone = null;
        this.address = null;
        this.historyId = null;
        this.habit = null;
    }
}
