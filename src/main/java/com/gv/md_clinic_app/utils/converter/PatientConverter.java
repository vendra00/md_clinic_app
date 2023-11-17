package com.gv.md_clinic_app.utils.converter;

import com.gv.md_clinic_app.model.Patient;
import com.gv.md_clinic_app.model.dto.PatientDto;
import com.gv.md_clinic_app.model.enums.UserRole;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatientConverter {
    public static PatientDto convertToDto(Patient patient, UserRole userRole) {
        log.info("Converting Patient to PatientDto");

        PatientDto dto = new PatientDto();

        dto.setId(patient.getId());
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setEmail(patient.getEmail());
        dto.setPhone(patient.getPhone());
        dto.setAddress(AddressConverter.convertToDto(patient.getAddress()));
        dto.setMedicalHistory(MedicalHistoryConverter.convertToDto(patient.getMedicalHistory()));
        dto.setConditions(ConditionConverter.convertToDto(patient.getConditions()));
        dto.setHistoryId(patient.getHistoryId());
        dto.setBloodType(patient.getBloodType());
        dto.setIsOrganDonor(patient.getIsOrganDonor());
        dto.setGender(patient.getGender());
        dto.setDob(patient.getDob());
        dto.setAge(patient.getAge());

        checkUserRoleTypeForSensitiveContent(userRole, dto);

        return dto;
    }

    private static void checkUserRoleTypeForSensitiveContent(UserRole userRole, PatientDto dto) {
        log.info("Checking user role type for sensitive content");

        if (userRole == UserRole.PATIENT) {
            log.info("Converting Patient to PatientDto for patient");
            dto.hideSensitiveDataForPatients();
        }

        if (userRole == UserRole.ADMIN) {
            log.info("Converting Patient to PatientDto for admin");
            dto.hideSensitiveDataForAdmins();
        }
    }

    public static Patient convertToEntity(PatientDto patientDto) {
        log.info("Converting PatientDto to Patient");

        Patient patient = new Patient();

        patient.setId(patientDto.getId());
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setEmail(patientDto.getEmail());
        patient.setPhone(patientDto.getPhone());
        patient.setAddress(AddressConverter.convertToEntity(patientDto.getAddress()));
        patient.setHistoryId(patientDto.getHistoryId());
        patient.setMedicalHistory(MedicalHistoryConverter.convertToEntity(patientDto.getMedicalHistory()));
        patient.setConditions(ConditionConverter.convertToEntity(patientDto.getConditions()));
        patient.setBloodType(patientDto.getBloodType());
        patient.setIsOrganDonor(patientDto.getIsOrganDonor());
        patient.setGender(patientDto.getGender());
        patient.setDob(patientDto.getDob());

        return patient;
    }
}
