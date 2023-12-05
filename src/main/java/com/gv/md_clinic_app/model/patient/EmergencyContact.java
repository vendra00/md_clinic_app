package com.gv.md_clinic_app.model.patient;

import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * EmergencyContact entity - embedded in Patient
 */
@Data
@Embeddable
public class EmergencyContact {
    private String firstName;
    private String lastName;
    private String phone;
}
