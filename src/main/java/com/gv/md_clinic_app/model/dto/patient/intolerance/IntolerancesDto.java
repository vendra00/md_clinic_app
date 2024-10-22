package com.gv.md_clinic_app.model.dto.patient.intolerance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IntolerancesDto {
    private FoodIntolerancesDto foodIntolerances;
    private DrugIntolerancesDto drugIntolerances;
}
