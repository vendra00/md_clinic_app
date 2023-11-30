package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

/**
 * AllergyType enum
 */
@Getter
public enum AllergyType {
    FOOD("Food"),
    MEDICINE("Medicine"),
    CONTACT("Contact"),
    OTHER("Other"),
    UNKNOWN("Unknown");

    private final String displayString;

    /**
     * AllergyType constructor
     * @param displayString String
     */
    AllergyType(String displayString) {this.displayString = displayString;}
}
