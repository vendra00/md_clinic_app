package com.gv.md_clinic_app.view;

import com.gv.md_clinic_app.view.form.patient.PatientFormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * PatientRegistrationView class is the view for the patient registration page.
 */
@PageTitle("Register Patient | MD Clinic")
@Route(value = "register-patient", layout = MainLayout.class)
public class PatientRegistrationView extends VerticalLayout {

    /**
     * Constructor for PatientRegistrationView class.
     * @param form The form layout for the patient registration page
     */
    @Autowired
    public PatientRegistrationView(PatientFormLayout form) {
        add(form);
    }
}
