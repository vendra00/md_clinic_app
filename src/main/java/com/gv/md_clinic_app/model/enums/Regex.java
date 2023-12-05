package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

/**
 * Regex enum class.
 * <p>
 * Date: May 2, 2021
 * <p>
 */
@Getter
public enum Regex {
    // Regular expression for phone number
    PHONE_NUMBER_CHECKER("\\+\\(\\d{3}\\) \\d{2}-\\d{3}-\\d{4}"),
    STRING_SANITIZER("<[^>]*>"),
    // Regular expression for only digits
    NUMERIC_PATTERN("^[0-9]+$"),
    // Regular expression for 4 to 10 digits number.
    NUMERIC_4_TO_10_DIGITS_PATTERN("^[0-9]{4,10}$"),
    // Regular expression for 5 digits number.
    ZIP_CODE_PATTERN("^[0-9]{5}(?:-[0-9]{4})?$"),
    // Allows letters, spaces, hyphens, and apostrophes
    PERSON_NAME_PATTERN("^[A-Za-zÀ-ÖØ-öø-ÿ'\\\\-\\\\s]+$");

    private final String displayString;

    /**
     * Constructor.
     * @param displayString String
     */
    Regex(String displayString) {
        this.displayString = displayString;
    }
}
