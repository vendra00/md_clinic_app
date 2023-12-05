package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

/**
 * Enum class for user roles.
 */
@Getter
public enum UserRole {
    ADMIN("ADMIN"),
    DOCTOR("DOCTOR"),
    PATIENT("PATIENT");

    /**
     * Name of the role.
     */
    UserRole(String name) {}
}
