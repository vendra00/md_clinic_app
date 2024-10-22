package com.gv.md_clinic_app.model.patient.allergy;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Allergies entity - embedded in Patient
 */
@Data
@Embeddable
public class FoodAllergies {
    @OneToMany
    private List<Allergy> allergies = new ArrayList<>();
}
