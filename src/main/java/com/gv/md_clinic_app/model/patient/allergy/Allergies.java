package com.gv.md_clinic_app.model.patient.allergy;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

/**
 * Allergies entity - embedded in Patient
 */
@Data
@Embeddable
public class Allergies {

    @Embedded
    private ContactAllergies contactAllergies;
    @Embedded
    private MedicalAllergies medicalAllergies;
    @Embedded
    private FoodAllergies foodAllergies;

}
