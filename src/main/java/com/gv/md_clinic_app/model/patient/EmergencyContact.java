package com.gv.md_clinic_app.model.patient;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class EmergencyContact {
    private String firstName;
    private String lastName;
    private String phone;
}
