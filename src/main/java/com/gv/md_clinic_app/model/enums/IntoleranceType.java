package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

@Getter
public enum IntoleranceType {
    FOOD("Food"),
    MEDICINE("Medicine"),
    OTHER("Other"),
    UNKNOWN("Unknown");

    private final String displayString;

    IntoleranceType(String displayString) {this.displayString = displayString;}
}
