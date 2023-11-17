package com.gv.md_clinic_app.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")
@PageTitle("MD Clinic")
@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {
    public MainLayout() {
        // Initialize the content layout area
        Div content = new Div();
        content.addClassName("content");
        content.setSizeFull();

        // Set the content layout as the main content of the AppLayout
        setContent(content);

        // Create the sidebar (menu)
        createSidebar();
    }

    private void createSidebar() {
        // Create a menu (sidebar) and add menu items
        Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.add(createTab("Dashboard", DashboardView.class));
        tabs.add(createTab("Doctor", DoctorView.class));
        // ... add other tabs for different views

        // Set the menu (sidebar) to the drawer of the AppLayout
        addToDrawer(tabs);
    }

    private Tab createTab(String text, Class<? extends Component> navigationTarget) {
        Tab tab = new Tab();
        RouterLink link = new RouterLink(text, navigationTarget);
        tab.add(link);
        return tab;
    }
}

