package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

/**
 * Enum class for dosage of the medicine.
 */
@Getter
public enum Dosage {
    LOW("Low"),
    NORMAL("Normal"),
    HIGH("High"),
    UNKNOWN("Unknown");

    private final String displayString;

    /**
     * Constructor for the enum.
     * @param displayString String to be displayed.
     */
    Dosage(String displayString) {this.displayString = displayString;}
}
