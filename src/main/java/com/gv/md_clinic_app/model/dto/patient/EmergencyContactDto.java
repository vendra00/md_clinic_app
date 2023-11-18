package com.gv.md_clinic_app.model.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContactDto {
    private String firstName;
    private String lastName;
    private String phone;
}
