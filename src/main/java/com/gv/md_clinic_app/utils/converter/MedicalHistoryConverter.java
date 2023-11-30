package com.gv.md_clinic_app.utils.converter;

import com.gv.md_clinic_app.model.patient.MedicalHistory;
import com.gv.md_clinic_app.model.dto.patient.MedicalHistoryDto;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * Converts MedicalHistory entities to MedicalHistoryDto and vice versa
 */
@Slf4j
public class MedicalHistoryConverter {

    /**
     * Converts a MedicalHistoryDto to a MedicalHistory entity
     * @param medicalHistoryDto the MedicalHistoryDto to be converted
     * @return the MedicalHistory entity
     */
    public static @NotNull MedicalHistory convertToEntity(@NotNull MedicalHistoryDto medicalHistoryDto) {
        log.info("Converting MedicalHistoryDto to MedicalHistory");

        MedicalHistory medicalHistory = new MedicalHistory();

        medicalHistory.setIsAllergic(medicalHistoryDto.getIsAllergic());
        medicalHistory.setIsIntolerant(medicalHistoryDto.getIsIntolerant());
        medicalHistory.setAllergies(AllergyConverter.convertToEntity(medicalHistoryDto.getAllergies()));
        medicalHistory.setIntolerances(IntoleranceConverter.convertToEntity(medicalHistoryDto.getIntolerances()));
        medicalHistory.setHospitalization(HospitalizationConverter.convertToEntity(medicalHistoryDto.getHospitalization()));

        return medicalHistory;
    }

    /**
     * Converts a MedicalHistory entity to a MedicalHistoryDto
     * @param medicalHistory the MedicalHistory entity to be converted
     * @return the MedicalHistoryDto
     */
    public static @NotNull MedicalHistoryDto convertToDto(@NotNull MedicalHistory medicalHistory) {
        log.info("Converting MedicalHistory to MedicalHistoryDto");

        MedicalHistoryDto dto = new MedicalHistoryDto();

        dto.setIsAllergic(medicalHistory.getIsAllergic());
        dto.setIsIntolerant(medicalHistory.getIsIntolerant());
        dto.setAllergies(AllergyConverter.convertToDto(medicalHistory.getAllergies()));
        dto.setIntolerances(IntoleranceConverter.convertToDto(medicalHistory.getIntolerances()));
        dto.setHospitalization(HospitalizationConverter.convertToDto(medicalHistory.getHospitalization()));

        return dto;
    }
}
