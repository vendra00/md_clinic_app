package com.gv.md_clinic_app.view.form.patient;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


/**
 * Hospitalization form record.
 */
public record HospitalizationFormRecord(HorizontalLayout hospitalDoctorLayout, HorizontalLayout reasonDescriptionLayout, HorizontalLayout dateLayout, HorizontalLayout zoneLayout, HorizontalLayout buttonLayout) {
}
