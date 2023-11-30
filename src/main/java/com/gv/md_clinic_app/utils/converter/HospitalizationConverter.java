package com.gv.md_clinic_app.utils.converter;

import com.gv.md_clinic_app.model.dto.patient.HospitalizationDto;
import com.gv.md_clinic_app.model.patient.Hospitalization;
import org.jetbrains.annotations.NotNull;

/**
 * Converts Hospitalization entities to HospitalizationDto and vice versa
 */
public class HospitalizationConverter {

    /**
     * Converts a HospitalizationDto to a Hospitalization entity
     * @param hospitalizationDto the HospitalizationDto to be converted
     * @return the Hospitalization entity
     */
    public static @NotNull Hospitalization convertToEntity(@NotNull HospitalizationDto hospitalizationDto) {

        Hospitalization hospitalization = new Hospitalization();

        hospitalization.setReason(hospitalizationDto.getReason());
        hospitalization.setDescription(hospitalizationDto.getDescription());
        hospitalization.setHospitalName(hospitalizationDto.getHospitalName());
        hospitalization.setDoctorName(hospitalizationDto.getDoctorName());
        hospitalization.setHospitalizationDate(hospitalizationDto.getHospitalizationDate());
        hospitalization.setDischargeDate(hospitalizationDto.getDischargeDate());
        hospitalization.setMedications(MedicationConverter.convertToEntity(hospitalizationDto.getMedications()));

        return hospitalization;
    }

    /**
     * Converts a Hospitalization entity to a HospitalizationDto
     * @param hospitalization the Hospitalization entity to be converted
     * @return the HospitalizationDto
     */
    public static @NotNull HospitalizationDto convertToDto(@NotNull Hospitalization hospitalization) {

        HospitalizationDto dto = new HospitalizationDto();

        dto.setReason(hospitalization.getReason());
        dto.setDescription(hospitalization.getDescription());
        dto.setHospitalName(hospitalization.getHospitalName());
        dto.setDoctorName(hospitalization.getDoctorName());
        dto.setHospitalizationDate(hospitalization.getHospitalizationDate());
        dto.setDischargeDate(hospitalization.getDischargeDate());
        dto.setMedications(MedicationConverter.convertToDto(hospitalization.getMedications()));

        return dto;
    }
}
