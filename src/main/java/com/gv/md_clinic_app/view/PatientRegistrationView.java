package com.gv.md_clinic_app.view;

import com.gv.md_clinic_app.view.form.PatientFormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "register-patient", layout = MainLayout.class)
public class PatientRegistrationView extends VerticalLayout {

    @Autowired
    public PatientRegistrationView(PatientFormLayout form) {
        add(form);
    }
}
