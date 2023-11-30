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
import org.jetbrains.annotations.NotNull;

/**
 * MainLayout class is the layout for the entire application.
 */
@Route("")
@PageTitle("MD Clinic")
@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {

    /**
     * Constructor for MainLayout class.
     */
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

    /**
     * Creates the sidebar (menu) for the application.
     */
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

    /**
     * Creates a tab for the sidebar (menu).
     * @param text the text to display on the tab
     * @param navigationTarget the view to navigate to when the tab is clicked
     * @return the tab
     */
    private @NotNull Tab createTab(String text, Class<? extends Component> navigationTarget) {
        Tab tab = new Tab();
        RouterLink link = new RouterLink(text, navigationTarget);
        tab.add(link);
        return tab;
    }
}

