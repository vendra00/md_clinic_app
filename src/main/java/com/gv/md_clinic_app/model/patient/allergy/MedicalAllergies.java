package com.gv.md_clinic_app.model.patient.allergy;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Embeddable
public class MedicalAllergies {
    @OneToMany
    private List<Allergy> allergies = new ArrayList<>();
}
