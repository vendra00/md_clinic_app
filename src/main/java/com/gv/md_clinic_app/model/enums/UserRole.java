package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ADMIN"),
    DOCTOR("DOCTOR"),
    PATIENT("PATIENT");

    UserRole(String name) {}
}
