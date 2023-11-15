package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

@Getter
public enum BloodType {
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-"),
    UNKNOWN("Unknown");

    private final String displayString;

    BloodType(String displayString) {
        this.displayString = displayString;
    }
}
