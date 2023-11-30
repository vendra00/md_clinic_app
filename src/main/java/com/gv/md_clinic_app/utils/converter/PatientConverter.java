package com.gv.md_clinic_app.utils.converter;

import com.gv.md_clinic_app.model.patient.Patient;
import com.gv.md_clinic_app.model.dto.patient.PatientDto;
import com.gv.md_clinic_app.model.enums.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * This class is used to convert a Patient to a PatientDto and vice versa.
 */
@Slf4j
public class PatientConverter {
    /**
     * This method is used to convert a Patient to a PatientDto.
     * @param patient - the Patient to be converted
     * @param userRole - the user role
     * @return dto - the converted PatientDto
     */
    public static @NotNull PatientDto convertToDto(@NotNull Patient patient, UserRole userRole) {
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

    /**
     * This method is used to check the user role type and hide sensitive content.
     * @param userRole - the user role
     * @param dto - the PatientDto
     */
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

    /**
     * This method is used to convert a PatientDto to a Patient.
     * @param patientDto - the PatientDto to be converted
     * @return patient - the converted Patient
     */
    public static @NotNull Patient convertToEntity(@NotNull PatientDto patientDto) {
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
