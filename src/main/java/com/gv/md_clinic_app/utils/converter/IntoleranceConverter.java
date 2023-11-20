package com.gv.md_clinic_app.utils.converter;

import com.gv.md_clinic_app.model.dto.patient.intolerance.IntolerancesDto;
import com.gv.md_clinic_app.model.patient.intolerance.Intolerances;

public class IntoleranceConverter {
    public static Intolerances convertToEntity(IntolerancesDto intolerancesDto) {

        Intolerances intolerances = new Intolerances();

        intolerances.setFoodIntolerances(FoodIntoleranceConverter.convertToEntity(intolerancesDto.getFoodIntolerances()));
        intolerances.setDrugIntolerances(DrugIntoleranceConverter.convertToEntity(intolerancesDto.getDrugIntolerances()));

        return intolerances;
    }

    public static IntolerancesDto convertToDto(Intolerances intolerances) {

        IntolerancesDto dto = new IntolerancesDto();

        dto.setFoodIntolerances(FoodIntoleranceConverter.convertToDto(intolerances.getFoodIntolerances()));
        dto.setDrugIntolerances(DrugIntoleranceConverter.convertToDto(intolerances.getDrugIntolerances()));

        return dto;

    }
}
