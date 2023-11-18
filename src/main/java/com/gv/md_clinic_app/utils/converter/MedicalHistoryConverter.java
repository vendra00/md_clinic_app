package com.gv.md_clinic_app.utils.converter;

import com.gv.md_clinic_app.model.patient.MedicalHistory;
import com.gv.md_clinic_app.model.dto.patient.MedicalHistoryDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MedicalHistoryConverter {
    public static MedicalHistory convertToEntity(MedicalHistoryDto medicalHistoryDto) {
        log.info("Converting MedicalHistoryDto to MedicalHistory");

        MedicalHistory medicalHistory = new MedicalHistory();

        //medicalHistory.setAllergies(medicalHistoryDto.getAllergies());

        return medicalHistory;
    }

    public static MedicalHistoryDto convertToDto(MedicalHistory medicalHistory) {
        log.info("Converting MedicalHistory to MedicalHistoryDto");

        MedicalHistoryDto dto = new MedicalHistoryDto();

        //dto.setAllergies(medicalHistory.getAllergies());

        return dto;
    }
}
