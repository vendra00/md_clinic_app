package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

@Getter
public enum Regex {
    PHONE_NUMBER_CHECKER("\\+\\(\\d{3}\\) \\d{2}-\\d{3}-\\d{4}"),
    STRING_SANITIZER("<[^>]*>");

    private final String displayString;

    Regex(String displayString) {
        this.displayString = displayString;
    }
}
