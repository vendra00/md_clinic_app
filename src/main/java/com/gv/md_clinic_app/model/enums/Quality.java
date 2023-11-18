package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

@Getter
public enum Quality {
    GOOD("Good"),
    BAD("Bad"),
    AVERAGE("Average"),
    UNKNOWN("Unknown");

    private final String displayString;

    Quality(String displayString) {this.displayString = displayString;}
}
