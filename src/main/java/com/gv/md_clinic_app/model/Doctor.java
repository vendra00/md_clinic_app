package com.gv.md_clinic_app.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Doctor extends Person {
    @NotNull @NotEmpty
    private String specialization;
    @NotNull @NotEmpty
    private String qualifications;
    @NotNull @NotEmpty
    private String workingHours;
}