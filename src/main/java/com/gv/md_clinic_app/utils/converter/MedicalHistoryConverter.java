package com.gv.md_clinic_app.utils.converter;

import com.gv.md_clinic_app.model.MedicalHistory;
import com.gv.md_clinic_app.model.dto.MedicalHistoryDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MedicalHistoryConverter {
    public static MedicalHistory convertToEntity(MedicalHistoryDto medicalHistoryDto) {
        log.info("Converting MedicalHistoryDto to MedicalHistory");

        MedicalHistory medicalHistory = new MedicalHistory();

        medicalHistory.setAllergies(medicalHistoryDto.getAllergies());
        medicalHistory.setChronicDiseases(medicalHistoryDto.getChronicDiseases());
        medicalHistory.setSurgeries(medicalHistoryDto.getSurgeries());
        medicalHistory.setFamilyHistory(medicalHistoryDto.getFamilyHistory());

        return medicalHistory;
    }

    public static MedicalHistoryDto convertToDto(MedicalHistory medicalHistory) {
        log.info("Converting MedicalHistory to MedicalHistoryDto");

        MedicalHistoryDto dto = new MedicalHistoryDto();

        dto.setAllergies(medicalHistory.getAllergies());
        dto.setChronicDiseases(medicalHistory.getChronicDiseases());
        dto.setSurgeries(medicalHistory.getSurgeries());
        dto.setFamilyHistory(medicalHistory.getFamilyHistory());

        return dto;
    }
}
