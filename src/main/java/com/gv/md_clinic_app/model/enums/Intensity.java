package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

/**
 * Enum class for intensity of the patient's health.
 */
@Getter
public enum Intensity {
    MILD("Mild"),
    MODERATE("Moderate"),
    SEVERE("Severe"),
    UNKNOWN("Unknown");

    private final String displayString;

    /**
     * Intensity of the patient's health.
     */
    Intensity(String displayString) {this.displayString = displayString;}
}
