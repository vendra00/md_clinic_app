package com.gv.md_clinic_app.model.dto.patient.intolerance;

import com.gv.md_clinic_app.model.dto.patient.allergy.AllergyDto;
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
public class FoodIntolerancesDto {
    private List<IntolerancesDto> intolerances = new ArrayList<>();
}
