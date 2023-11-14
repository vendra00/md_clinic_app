package com.gv.md_clinic_app.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
public class EmergencyContact {
    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private String phone;
}
