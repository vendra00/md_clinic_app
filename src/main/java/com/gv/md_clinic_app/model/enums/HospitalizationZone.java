package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

/**
 * Enum for the hospitalization zone.
 */
@Getter
public enum HospitalizationZone {
    ICU ("Intensive Care Unit (ICU)"),
    ER("Emergency Room (ER)"),
    GENERAL_WARD("General or Medical Ward"),
    SURGICAL_WARD("Surgical Ward"),
    PEDIATRIC_WARD("Pediatric Ward"),
    MATERNITY_WARD("Maternity Ward or Obstetrics/Gynecology Ward"),
    PSYCHIATRIC_WARD("Psychiatric Ward or Behavioral Health Unit"),
    REHABILITATION_UNIT("Rehabilitation Unit"),
    ONCOLOGY_WARD("Oncology Ward"),
    CARDIOLOGY_WARD("Cardiology Ward"),
    GERIATRIC_WARD("Geriatric Ward"),
    NEUROLOGY_WARD("Neurology Ward"),
    CCU("Coronary Care Unit (CCU)"),
    OUTPATIENT_DEPARTMENT("Outpatient Department (OPD)"),
    UNKNOWN("Unknown"),
    OTHER("Other");

    private final String displayString;

    /**
     * Constructor for the enum.
     * @param displayString String to be displayed.
     */
    HospitalizationZone(String displayString) {this.displayString = displayString;}
}
