package com.gv.md_clinic_app.model.dto;

import com.gv.md_clinic_app.model.enums.States;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String street;
    private String city;
    private States state;
    private String zipCode;
}
