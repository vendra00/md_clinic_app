package com.gv.md_clinic_app.view.form;

import com.gv.md_clinic_app.model.enums.AllergyType;
import com.gv.md_clinic_app.model.enums.IntoleranceType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Form for the intolerance.
 */
@Slf4j
@CssImport("./styles/patient-form-styles.css")
@Getter
@Setter
class IntoleranceForm extends VerticalLayout {

    private final ComboBox<IntoleranceType> intoleranceType = new ComboBox<>("Intolerance Type");
    private final TextField intoleranceName = new TextField("Intolerance Name");
    private final TextArea intoleranceDescription = new TextArea("Description");
    private final Button saveButton = new Button("Save");
    private final Button closeButton = new Button("Close");

    /**
     * Constructor for the intolerance form.
     * @param parentDialog Parent dialog.
     */
    public IntoleranceForm(Dialog parentDialog) {
        log.debug("IntoleranceForm initialized");
        comboBoxValuesSetUp();
        closeButton.addClickListener(event -> parentDialog.close());
        // Layout for buttons
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        buttonLayout.setWidthFull(); // Set the width to fill the container
        buttonLayout.setJustifyContentMode(JustifyContentMode.CENTER); // Align to the end (right)
        buttonLayout.add(saveButton, closeButton); // Add both buttons to the layout

        // Add all components to the form
        add(intoleranceType, intoleranceName, intoleranceDescription, buttonLayout); // Add the button layout instead of individual buttons
    }

    /**
     * Set up the combo boxes.
     */
    private void comboBoxValuesSetUp() {
        log.debug("Setting up the combo boxes");
        // Set the allergyType values of the combo boxes
        intoleranceType.setItems(IntoleranceType.values());
        intoleranceType.setItemLabelGenerator(IntoleranceType::getDisplayString);
    }

}
