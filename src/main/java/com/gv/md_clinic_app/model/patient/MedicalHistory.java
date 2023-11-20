package com.gv.md_clinic_app.model.patient;

import com.gv.md_clinic_app.model.patient.allergy.Allergies;
import com.gv.md_clinic_app.model.enums.Choice;
import com.gv.md_clinic_app.model.patient.intolerance.Intolerances;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
@Embeddable
public class MedicalHistory {
    private Choice isAllergic;
    private Choice isIntolerant;

    @Embedded
    private Allergies allergies;
    @Embedded
    private Intolerances intolerances;
}
