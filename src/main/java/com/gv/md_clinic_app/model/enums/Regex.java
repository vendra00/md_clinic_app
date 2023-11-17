package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

@Getter
public enum Regex {
    PHONE_NUMBER_CHECKER("\\+\\(\\d{3}\\) \\d{2}-\\d{3}-\\d{4}"),
    STRING_SANITIZER("<[^>]*>"),
    // Regular expression for only digits
    NUMERIC_PATTERN("^[0-9]+$"),
    NUMERIC_4_TO_10_DIGITS_PATTERN("\\\\d{4,10}"),
    // Allows letters, spaces, hyphens, and apostrophes
    PERSON_NAME_PATTERN("^[A-Za-zÀ-ÖØ-öø-ÿ'\\\\-\\\\s]+$");

    private final String displayString;

    Regex(String displayString) {
        this.displayString = displayString;
    }
}
