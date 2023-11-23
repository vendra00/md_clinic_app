package com.gv.md_clinic_app.view.form;

import com.gv.md_clinic_app.model.enums.IntoleranceType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CssImport("./styles/patient-form-styles.css")
@Getter
@Setter
public class IntoleranceForm extends VerticalLayout {

    // Create the components
    private final ComboBox<IntoleranceType> intoleranceType = new ComboBox<>("Intolerance Type");
    private final TextField intoleranceName = new TextField("Intolerance Name");
    private final TextArea intoleranceDescription = new TextArea("Description");
    private final Button saveButton = new Button("Save");
    private final Button closeButton = new Button("Close");

    public IntoleranceForm(Dialog parentDialog) {
        log.debug("IntoleranceForm initialized");
        comboBoxValuesSetUp();
        closeButton.addClickListener(event -> parentDialog.close());
        // Layout for buttons
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        buttonLayout.add(saveButton, closeButton);

        // Set the helper text for the combo box
        intoleranceType.setHelperText("Select intolerance type");
        // Set the combo box as required
        intoleranceType.setRequired(true);

        // Add all components to the form
        add(intoleranceType, intoleranceName, intoleranceDescription, buttonLayout);
    }

    private void comboBoxValuesSetUp() {
        log.debug("Setting up the combo boxes");
        // Set the intoleranceType values of the combo boxes
        intoleranceType.setItems(IntoleranceType.values());
        intoleranceType.setItemLabelGenerator(IntoleranceType::getDisplayString);
    }
}
