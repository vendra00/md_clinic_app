package com.gv.md_clinic_app.model;

import com.gv.md_clinic_app.model.enums.States;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
public class Address {
    @NotNull @NotEmpty
    private String street;
    @NotNull @NotEmpty
    private String city;
    @NotNull @NotEmpty
    private States state;
    @NotNull @NotEmpty
    private String zipCode;
}
