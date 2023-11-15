package com.gv.md_clinic_app.model.dto;

import com.gv.md_clinic_app.model.enums.Choice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryDto {
    private Choice allergies;
    private Choice chronicDiseases;
    private Choice surgeries;
    private Choice familyHistory;
}
