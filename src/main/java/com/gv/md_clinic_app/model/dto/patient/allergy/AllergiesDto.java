package com.gv.md_clinic_app.model.dto.patient.allergy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object for Allergies entity.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AllergiesDto {
    private ContactAllergiesDto contactAllergies;
    private FoodAllergiesDto foodAllergies;
    private MedicalAllergiesDto drugAllergies;
}
