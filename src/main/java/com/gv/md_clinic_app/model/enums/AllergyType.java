package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

@Getter
public enum AllergyType {
    FOOD("Food"),
    MEDICINE("Medicine"),
    CONTACT("Contact"),
    OTHER("Other"),
    UNKNOWN("Unknown");

    private final String displayString;

    AllergyType(String displayString) {this.displayString = displayString;}
}
