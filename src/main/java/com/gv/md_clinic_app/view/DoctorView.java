package com.gv.md_clinic_app.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * DoctorView class is the view for the doctor page.
 */
@PageTitle("Doctor Menu | MD Clinic")
@Route(value = "doctor", layout = MainLayout.class)
public class DoctorView extends VerticalLayout {

    /**
     * Constructor for DoctorView class.
     */
    public DoctorView() {
        // Create a button that will navigate to the "register-patient" route when clicked
        Button registerPatientButton = new Button("Register Patient", event -> {
            UI.getCurrent().navigate("register-patient");
        });

        // Add the button to the view
        add(registerPatientButton);
    }
}

