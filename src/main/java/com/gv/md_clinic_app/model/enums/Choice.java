package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

@Getter
public enum Choice {
    YES("Yes"),
    NO("No"),
    UNKNOWN("Unknown");

    private final String displayString;

    Choice(String displayString) {
        this.displayString = displayString;
    }
}
