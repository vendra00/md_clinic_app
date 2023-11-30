package com.gv.md_clinic_app.model.patient;

import com.gv.md_clinic_app.model.*;
import com.gv.md_clinic_app.model.enums.BloodType;
import com.gv.md_clinic_app.model.enums.Choice;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a patient
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Patient extends Person {
    @Embedded
    private MedicalHistory medicalHistory;
    @Embedded
    private EmergencyContact emergencyContact;
    @Embedded
    private Habit habit;
    @ManyToMany
    @JoinTable(
            name = "patient_condition",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "condition_id")
    )
    @ToString.Exclude
    private Set<Condition> conditions = new HashSet<>();
    private double weight;
    private double height;
    private BloodType bloodType;
    private Choice isOrganDonor;
    @Past
    private LocalDate dob;

    /**
     * Calculates the age of the patient
     * @return the age of the patient
     */
    public int getAge() {
        if (dob == null) {
            return 0;
        }
        return Period.between(dob, LocalDate.now()).getYears();
    }

    /**
     * Calculates the BMI of the patient
     * @return the BMI of the patient
     */
    public double getBMI() {
        if (weight == 0 || height == 0) {
            return 0;
        }
        return weight / (height * height);
    }
}
