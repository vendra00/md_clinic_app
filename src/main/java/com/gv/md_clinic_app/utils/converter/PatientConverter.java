package com.gv.md_clinic_app.utils.converter;

import com.gv.md_clinic_app.model.patient.Patient;
import com.gv.md_clinic_app.model.dto.patient.PatientDto;
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
        dto.setEmergencyContact(EmergencyContactConverter.convertToDto(patient.getEmergencyContact()));
        dto.setHabit(HabitConverter.convertToDto(patient.getHabit()));
        dto.setMedicalHistory(MedicalHistoryConverter.convertToDto(patient.getMedicalHistory()));
        dto.setConditions(ConditionConverter.convertToDto(patient.getConditions()));
        dto.setWeight(patient.getWeight());
        dto.setHeight(patient.getHeight());
        dto.setBloodType(patient.getBloodType());
        dto.setIsOrganDonor(patient.getIsOrganDonor());
        dto.setGender(patient.getGender());
        dto.setDob(patient.getDob());
        dto.setAge(patient.getAge());
        dto.setImc(patient.getBMI());

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
        patient.setEmergencyContact(EmergencyContactConverter.convertToEntity(patientDto.getEmergencyContact()));
        patient.setAddress(AddressConverter.convertToEntity(patientDto.getAddress()));
        patient.setHabit(HabitConverter.convertToEntity(patientDto.getHabit()));
        patient.setWeight(patientDto.getWeight());
        patient.setHeight(patientDto.getHeight());
        patient.setMedicalHistory(MedicalHistoryConverter.convertToEntity(patientDto.getMedicalHistory()));
        patient.setConditions(ConditionConverter.convertToEntity(patientDto.getConditions()));
        patient.setBloodType(patientDto.getBloodType());
        patient.setIsOrganDonor(patientDto.getIsOrganDonor());
        patient.setGender(patientDto.getGender());
        patient.setDob(patientDto.getDob());

        return patient;
    }
}
