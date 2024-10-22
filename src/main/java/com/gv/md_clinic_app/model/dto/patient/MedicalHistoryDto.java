package com.gv.md_clinic_app.model.dto.patient;

import com.gv.md_clinic_app.model.dto.patient.allergy.AllergiesDto;
import com.gv.md_clinic_app.model.dto.patient.intolerance.IntolerancesDto;
import com.gv.md_clinic_app.model.enums.Choice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object for medical history.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryDto {
    private Choice isAllergic;
    private Choice isIntolerant;
    private AllergiesDto allergies;
    private IntolerancesDto intolerances;
    private HospitalizationDto hospitalization;
}
