package com.gv.md_clinic_app.model.dto.patient.intolerance;

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
public class DrugIntolerancesDto {
    private List<IntolerancesDto> intolerances = new ArrayList<>();
}
