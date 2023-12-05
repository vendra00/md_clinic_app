package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

/**
 * Enum class for intolerance types.
 */
@Getter
public enum IntoleranceType {
    FOOD("Food"),
    MEDICINE("Medicine"),
    OTHER("Other"),
    UNKNOWN("Unknown");

    private final String displayString;

    /**
     * Intolerance type.
     */
    IntoleranceType(String displayString) {this.displayString = displayString;}
}
