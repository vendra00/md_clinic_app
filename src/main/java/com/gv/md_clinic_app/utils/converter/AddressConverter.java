package com.gv.md_clinic_app.utils.converter;

import com.gv.md_clinic_app.model.Address;
import com.gv.md_clinic_app.model.dto.AddressDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddressConverter {

    public static Address convertToEntity(AddressDto addressDto) {
        log.info("AddressConverter.convertToEntity() - addressDto: {}", addressDto);

        Address address = new Address();

        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setZipCode(addressDto.getZipCode());

        return address;
    }

    public static AddressDto convertToDto(Address address) {
        log.info("AddressConverter.convertToDto() - address: {}", address);

        AddressDto dto = new AddressDto();

        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setZipCode(address.getZipCode());

        return dto;
    }
}

