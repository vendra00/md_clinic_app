package com.gv.md_clinic_app.model.patient.intolerance;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
@Embeddable
public class Intolerances {
    @Embedded
    private FoodIntolerances foodIntolerances;
    @Embedded
    private DrugIntolerances drugIntolerances;
}