package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

/**
 * Enum class for choices.
 */
@Getter
public enum Choice {
    YES("Yes"),
    NO("No"),
    UNKNOWN("Unknown");

    private final String displayString;

    /**
     * Constructor for Choice enum.
     * @param displayString String to be displayed.
     */
    Choice(String displayString) {
        this.displayString = displayString;
    }
}
