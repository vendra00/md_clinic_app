package com.gv.md_clinic_app.model;

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
    @ManyToMany
    @JoinTable(
            name = "patient_condition",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "condition_id")
    )
    @ToString.Exclude
    private Set<Condition> conditions = new HashSet<>();
    private String historyId;
    private double weight;
    private double height;
    private BloodType bloodType;
    private Choice isOrganDonor;
    @Past
    private LocalDate dob;
    public int getAge() {
        if (dob == null) {
            return 0;
        }
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public double getIMC() {
        if (weight == 0 || height == 0) {
            return 0;
        }
        return weight / (height * height);
    }
}
