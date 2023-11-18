package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    UNKNOWN("Unknown");

    private final String displayString;

    Gender(String displayString) {
        this.displayString = displayString;
    }
}
