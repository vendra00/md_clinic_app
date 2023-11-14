package com.gv.md_clinic_app.model;

import com.gv.md_clinic_app.model.enums.BloodType;
import com.gv.md_clinic_app.model.enums.Choice;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Data
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
    private Set<Condition> conditions = new HashSet<>();
    private BloodType bloodType;
    @NotNull @NotEmpty
    private Choice isOrganDonor;
    @Past
    private LocalDate dob;
    public int getAge() {
        if (dob == null) {
            return 0;
        }
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
