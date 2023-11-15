package com.gv.md_clinic_app.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "doctor", layout = MainLayout.class)
public class DoctorView extends VerticalLayout {

    public DoctorView() {
        // Create a button that will navigate to the "register-patient" route when clicked
        Button registerPatientButton = new Button("Register Patient", event -> {
            UI.getCurrent().navigate("register-patient");
        });

        // Add the button to the view
        add(registerPatientButton);
    }
}

