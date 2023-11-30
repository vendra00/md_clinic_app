package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

@Getter
public enum Dosage {
    LOW("Low"),
    NORMAL("Normal"),
    HIGH("High"),
    UNKNOWN("Unknown");

    private final String displayString;

    Dosage(String displayString) {this.displayString = displayString;}
}
