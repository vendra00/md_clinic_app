package com.gv.md_clinic_app.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Dashboard | MD Clinic")
@Route(value = "dashboard", layout = MainLayout.class)
public class DashboardView extends VerticalLayout {

    public DashboardView() {
        // Your view content goes here
        add(new Text("This is the Dashboard view"));
    }
}
