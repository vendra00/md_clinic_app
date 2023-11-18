package com.gv.md_clinic_app.model.patient.intolerance;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Embeddable
public class FoodIntolerances {
    @OneToMany
    private List<Intolerance> intolerances = new ArrayList<>();
}
