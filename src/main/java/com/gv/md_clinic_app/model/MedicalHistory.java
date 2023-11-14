package com.gv.md_clinic_app.model;

import com.gv.md_clinic_app.model.enums.Choice;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class MedicalHistory {
    private Choice allergies;
    private Choice chronicDiseases;
    private Choice surgeries;
    private Choice familyHistory;
}
