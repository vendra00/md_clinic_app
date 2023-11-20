package com.gv.md_clinic_app.utils.converter;

import com.gv.md_clinic_app.model.patient.MedicalHistory;
import com.gv.md_clinic_app.model.dto.patient.MedicalHistoryDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MedicalHistoryConverter {
    public static MedicalHistory convertToEntity(MedicalHistoryDto medicalHistoryDto) {
        log.info("Converting MedicalHistoryDto to MedicalHistory");

        MedicalHistory medicalHistory = new MedicalHistory();

        medicalHistory.setIsAllergic(medicalHistoryDto.getIsAllergic());
        medicalHistory.setIsIntolerant(medicalHistoryDto.getIsIntolerant());
        medicalHistory.setAllergies(AllergyConverter.convertToEntity(medicalHistoryDto.getAllergies()));
        medicalHistory.setIntolerances(IntoleranceConverter.convertToEntity(medicalHistoryDto.getIntolerances()));

        return medicalHistory;
    }

    public static MedicalHistoryDto convertToDto(MedicalHistory medicalHistory) {
        log.info("Converting MedicalHistory to MedicalHistoryDto");

        MedicalHistoryDto dto = new MedicalHistoryDto();

        dto.setIsAllergic(medicalHistory.getIsAllergic());
        dto.setIsIntolerant(medicalHistory.getIsIntolerant());
        dto.setAllergies(AllergyConverter.convertToDto(medicalHistory.getAllergies()));
        dto.setIntolerances(IntoleranceConverter.convertToDto(medicalHistory.getIntolerances()));

        return dto;
    }
}
