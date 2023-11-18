package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

@Getter
public enum Intensity {
    MILD("Mild"),
    MODERATE("Moderate"),
    SEVERE("Severe"),
    UNKNOWN("Unknown");

    private final String displayString;

    Intensity(String displayString) {this.displayString = displayString;}
}
