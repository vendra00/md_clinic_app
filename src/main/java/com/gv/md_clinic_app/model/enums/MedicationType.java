package com.gv.md_clinic_app.model.enums;

import lombok.Getter;

@Getter
public enum MedicationType {
    Analgesics("Analgesics"),
    Sedatives("Sedatives"),
    Paralytics("Paralytics"),
    Antibiotics("Antibiotics"),
    VasopressorsAndInotropes("Vasopressors and Inotropes"),
    Diuretics("Diuretics"),
    Anticoagulants("Anticoagulants"),
    GastrointestinalProphylaxis("Gastrointestinal Prophylaxis"),
    BloodProducts("Blood Products"),
    ElectrolyteReplacements("Electrolyte Replacements"),
    Insulin("Insulin"),
    Bronchodilators("Bronchodilators");

    private final String displayString;

    /**
     * Constructor for the enum.
     * @param displayString String to be displayed.
     */
    MedicationType(String displayString) {this.displayString = displayString;}

}
