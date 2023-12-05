package com.gv.md_clinic_app.model.patient.intolerance;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Intolerances entity - embedded in Patient
 */
@Data
@Embeddable
public class DrugIntolerances {
    @OneToMany
    private List<Intolerance> intolerances = new ArrayList<>();
}
