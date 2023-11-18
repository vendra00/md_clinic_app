package com.gv.md_clinic_app.utils.converter;

import com.gv.md_clinic_app.model.patient.EmergencyContact;
import com.gv.md_clinic_app.model.dto.patient.EmergencyContactDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmergencyContactConverter {
    public static EmergencyContact convertToEntity(EmergencyContactDto emergencyContactDto) {
        log.info("AddressConverter.convertToEntity() - addressDto: {}", emergencyContactDto);

        EmergencyContact emergencyContact = new EmergencyContact();

        emergencyContact.setFirstName(emergencyContactDto.getFirstName());
        emergencyContact.setLastName(emergencyContactDto.getLastName());
        emergencyContact.setPhone(emergencyContactDto.getPhone());

        return emergencyContact;
    }

    public static EmergencyContactDto convertToDto(EmergencyContact emergencyContact) {
        log.info("AddressConverter.convertToDto() - address: {}", emergencyContact);

        EmergencyContactDto dto = new EmergencyContactDto();

        dto.setFirstName(emergencyContact.getFirstName());
        dto.setLastName(emergencyContact.getLastName());
        dto.setPhone(emergencyContact.getPhone());

        return dto;
    }
}
