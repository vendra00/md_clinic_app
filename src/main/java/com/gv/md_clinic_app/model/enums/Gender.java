package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

/**
 * Gender enum class.
 */
@Getter
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    UNKNOWN("Unknown");

    private final String displayString;

    /**
     * Constructor
     * @param displayString string
     */
    Gender(String displayString) {this.displayString = displayString;}
}
