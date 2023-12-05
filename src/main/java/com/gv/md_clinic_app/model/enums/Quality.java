package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

/**
 * Enum class for quality of the patient's health.
 */
@Getter
public enum Quality {
    GOOD("Good"),
    BAD("Bad"),
    AVERAGE("Average"),
    UNKNOWN("Unknown");

    private final String displayString;

    /**
     * Quality of the patient's health.
     */
    Quality(String displayString) {this.displayString = displayString;}
}
