package com.gv.md_clinic_app.model.dto.patient.allergy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalAllergiesDto {
    private List<AllergyDto> allergies = new ArrayList<>();
}
