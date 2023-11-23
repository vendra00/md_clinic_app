package com.gv.md_clinic_app.view.form;

import com.gv.md_clinic_app.model.enums.AllergyType;
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

@Slf4j
@CssImport("./styles/patient-form-styles.css")
@Getter @Setter
class AllergyForm extends VerticalLayout {

    // Create the components for the form
    private final ComboBox<AllergyType> allergyType = new ComboBox<>("Allergy Type");
    private final TextField allergyName = new TextField("Allergy Name");
    private final TextArea allergyDescription = new TextArea("Description");
    private final Button saveButton = new Button("Save");
    private final Button closeButton = new Button("Close");

    /**
     * Constructor for the AllergyForm class.
     * @param parentDialog - the dialog in which the form is displayed.
     */
    public AllergyForm(Dialog parentDialog) {
        log.debug("AllergyForm initialized");
        comboBoxValuesSetUp();
        closeButton.addClickListener(event -> parentDialog.close());
        // Layout for buttons
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        buttonLayout.setWidthFull(); // Set the width to fill the container
        buttonLayout.setJustifyContentMode(JustifyContentMode.CENTER); // Align to the end (right)
        buttonLayout.add(saveButton, closeButton); // Add both buttons to the layout

        // Set the helper text for the combo box
        allergyType.setHelperText("Select allergy type");
        // Set the combo box as required
        allergyType.setRequired(true);

        // Add all components to the form
        add(allergyType, allergyName, allergyDescription, buttonLayout); // Add the button layout instead of individual buttons
    }

    /**
     * Method to set up the combo boxes in the form.
     */
    private void comboBoxValuesSetUp() {
        log.debug("Setting up the combo boxes");
        // Set the allergyType values of the combo boxes
        allergyType.setItems(AllergyType.values());
        allergyType.setItemLabelGenerator(AllergyType::getDisplayString);
    }

}
