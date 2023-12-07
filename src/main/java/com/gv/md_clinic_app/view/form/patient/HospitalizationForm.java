package com.gv.md_clinic_app.view.form.patient;

import com.gv.md_clinic_app.model.enums.HospitalizationZone;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * Hospitalization form.
 */
@Slf4j
@CssImport("./styles/patient-form-styles.css")
@Getter
@Setter
public class HospitalizationForm extends VerticalLayout {

    //TextField Fields
    private final TextField hospitalName = new TextField("Hospital Name");
    private final TextField doctorName = new TextField("Doctor Name");

    //TextArea Fields
    private final TextArea hospitalizationReason = new TextArea("Reason");
    private final TextArea hospitalizationDescription = new TextArea("Description");
    private final TextArea selectedHospitalizationZones = new TextArea("Selected Zones");

    //MultiSelectComboBox Fields
    private final MultiSelectComboBox<HospitalizationZone> hospitalizationZone = new MultiSelectComboBox<>("Hospitalization Zone");

    //DatePicker Fields
    private final DatePicker hospitalizationDate = new DatePicker("Hospitalization Date");
    private final DatePicker dischargeDate = new DatePicker("Hospitalization Discharge Date");

    //Button Fields
    private final Button saveButton = new Button("Save");
    private final Button closeButton = new Button("Close");

    /**
     * Hospitalization form constructor.
     * @param parentDialog Parent dialog.
     */
    public HospitalizationForm(Dialog parentDialog) {
        log.debug("HospitalizationForm initialized");

        // Set the form properties
        comboBoxValuesSetUp();
        initializeDateValidators();
        selectedHospitalizationZonesSetUp();
        closeButton.addClickListener(event -> parentDialog.close());

        // Set the form properties
        HospitalizationFormRecord hospitalizationFormRecord = getHospitalizationForm();

        // Add all components to the form
        add(hospitalizationFormRecord.hospitalDoctorLayout(), hospitalizationFormRecord.reasonDescriptionLayout(), hospitalizationFormRecord.zoneLayout(), hospitalizationFormRecord.dateLayout(), hospitalizationFormRecord.buttonLayout());
    }

    /**
     * Set up the selected hospitalization zones.
     */
    private void selectedHospitalizationZonesSetUp() {
        selectedHospitalizationZones.setReadOnly(true);
        hospitalizationZone.addValueChangeListener(e -> {
            String selectedCountriesText = e.getValue().stream()
                    .map(HospitalizationZone::getDisplayString).collect(Collectors.joining(", "));

            selectedHospitalizationZones.setValue(selectedCountriesText);
        });
    }

    /**
     * Get the hospitalization form.
     * @return HospitalizationFormRecord
     */
    private @NotNull HospitalizationFormRecord getHospitalizationForm() {
        HorizontalLayout hospitalDoctorLayout = getHospitalDoctorLayout();
        HorizontalLayout reasonDescriptionLayout = getReasonDescriptionLayout();
        HorizontalLayout dateLayout = getDateLayout();
        HorizontalLayout zoneLayout = getZoneLayout();
        HorizontalLayout buttonLayout = getButtonLayout();
        return new HospitalizationFormRecord(hospitalDoctorLayout, reasonDescriptionLayout, dateLayout, zoneLayout, buttonLayout);
    }

    /**
     * Get the zone layout.
     * @return HorizontalLayout
     */
    private @NotNull HorizontalLayout getZoneLayout() {
        HorizontalLayout zoneLayout = new HorizontalLayout();
        zoneLayout.add(hospitalizationZone, selectedHospitalizationZones);
        zoneLayout.setAlignItems(Alignment.BASELINE);
        return zoneLayout;
    }

    /**
     * Get the date layout.
     * @return HorizontalLayout
     */
    private @NotNull HorizontalLayout getDateLayout() {
        HorizontalLayout dateLayout = new HorizontalLayout();
        dateLayout.add(hospitalizationDate, dischargeDate);
        dateLayout.setAlignItems(Alignment.BASELINE);
        return dateLayout;
    }

    /**
     * Get the reason and description layout.
     * @return HorizontalLayout
     */
    private @NotNull HorizontalLayout getReasonDescriptionLayout() {
        HorizontalLayout reasonDescriptionLayout = new HorizontalLayout();
        reasonDescriptionLayout.add(hospitalizationReason, hospitalizationDescription);
        reasonDescriptionLayout.setAlignItems(Alignment.BASELINE);
        return reasonDescriptionLayout;
    }

    /**
     * Get the hospital and doctor layout.
     * @return HorizontalLayout
     */
    private @NotNull HorizontalLayout getHospitalDoctorLayout() {
        HorizontalLayout hospitalDoctorLayout = new HorizontalLayout();
        hospitalDoctorLayout.add(hospitalName, doctorName);
        hospitalDoctorLayout.setAlignItems(Alignment.BASELINE);
        return hospitalDoctorLayout;
    }

    /**
     * Get the button layout.
     * @return HorizontalLayout
     */
    private @NotNull HorizontalLayout getButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        buttonLayout.setWidthFull(); // Set the width to fill the container
        buttonLayout.setJustifyContentMode(JustifyContentMode.CENTER); // Align to the end (right)
        buttonLayout.add(saveButton, closeButton); // Add both buttons to the layout
        return buttonLayout;
    }

    /**
     * Set up the combo boxes.
     */
    private void comboBoxValuesSetUp() {
        log.debug("Setting up the combo boxes");
        // Set the allergyType values of the combo boxes
        hospitalizationZone.setItems(HospitalizationZone.values());
        hospitalizationZone.setItemLabelGenerator(HospitalizationZone::getDisplayString);
    }

    /**
     * Initialize the date validators.
     */
    private void initializeDateValidators() {
        LocalDate today = LocalDate.now();

        // Validator for the hospitalization date
        hospitalizationDate.setRequired(true);
        hospitalizationDate.setErrorMessage("Hospitalization date is required");
        hospitalizationDate.setMin(today.minusYears(120)); // Assuming hospitalization can't be more than 120 years ago
        hospitalizationDate.setMax(today);
        hospitalizationDate.setInvalid(false); // Initialize as valid

        // Validator for the discharge date
        dischargeDate.setRequiredIndicatorVisible(true);
        dischargeDate.setErrorMessage("Discharge date is required and can't be before the hospitalization date or in the future");
        dischargeDate.setInvalid(false); // Initialize as valid

        // Adding value change listeners to perform cross-field validation
        hospitalizationDate.addValueChangeListener(event -> validateDates());
        dischargeDate.addValueChangeListener(event -> validateDates());
    }

    /**
     * Validate the dates.
     */
    private void validateDates() {
        boolean isValid = true;

        LocalDate hospitalization = hospitalizationDate.getValue();
        LocalDate discharge = dischargeDate.getValue();

        // Check if both dates are entered
        if (hospitalization == null || discharge == null) {
            isValid = false;
        }

        // Check that hospitalization date is not after discharge date
        if (hospitalization != null && discharge != null && !hospitalization.isBefore(discharge)) {
            hospitalizationDate.setInvalid(true);
            dischargeDate.setInvalid(true);
            isValid = false;
        } else {
            hospitalizationDate.setInvalid(false);
            dischargeDate.setInvalid(false);
        }

        // Check that dates are not in the future
        if ((hospitalization != null && hospitalization.isAfter(LocalDate.now())) ||
                (discharge != null && discharge.isAfter(LocalDate.now()))) {
            isValid = false;
        }

        // Optionally disable the save button if the dates are invalid
        saveButton.setEnabled(isValid);
    }
}
