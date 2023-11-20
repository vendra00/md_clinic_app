package com.gv.md_clinic_app.utils.converter;

import com.gv.md_clinic_app.model.dto.patient.intolerance.DrugIntolerancesDto;
import com.gv.md_clinic_app.model.patient.intolerance.DrugIntolerances;
import com.gv.md_clinic_app.model.patient.intolerance.Intolerance;

import java.util.ArrayList;
import java.util.List;

public class DrugIntoleranceConverter {
    public static DrugIntolerances convertToEntity(DrugIntolerancesDto drugIntolerancesDto) {

        DrugIntolerances drugIntolerances = new DrugIntolerances();

        List<Intolerance> intolerances = new ArrayList<>();


        return drugIntolerances;
    }

    public static DrugIntolerancesDto convertToDto(DrugIntolerances drugIntolerances) {
        return null;
    }
}
