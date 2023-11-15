package com.gv.md_clinic_app.view.form;

import com.gv.md_clinic_app.model.dto.PatientDto;
import com.gv.md_clinic_app.model.enums.BloodType;
import com.gv.md_clinic_app.model.enums.Choice;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@SpringComponent
@UIScope
public class PatientFormLayout extends VerticalLayout {

    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8080/doctors/register-patient";

    //Basic Information
    private final TextField firstName = new TextField("First Name");
    private final TextField lastName = new TextField("Last Name");
    private final TextField email = new TextField("Email");
    private final TextField phone = new TextField("Phone");
    private final DatePicker dob = new DatePicker("Date of Birth");
    private final TextField historyId = new TextField("History Number");

    //Secondary Information
    private final ComboBox<BloodType> bloodType = new ComboBox<>("Blood Type");
    private final ComboBox<Choice> isOrganDonor = new ComboBox<>("Organ Donor");
    //private final TextField emergencyContactName = new TextField("Emergency Contact Name");
    //private final TextField address = new TextField("Address");
    private final Button saveButton = new Button("Register");

    private final Binder<PatientDto> binder = new Binder<>(PatientDto.class);

    @Autowired
    public PatientFormLayout(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        FormLayout formLayout = new FormLayout();
        formLayout.add(firstName, lastName, email, phone, dob, historyId, bloodType, isOrganDonor);

        phone.getElement().executeJs(
                "this.addEventListener('input', function() {" +
                        "  var value = this.value.replace(/[^0-9]/g, '');" +
                        "  if (value.length > 2) {" +
                        "    value = '+(' + value.slice(0, 2) + ') ' + value.slice(2);" +
                        "  }" +
                        "  if (value.length > 8) {" +
                        "    value = value.slice(0, 9) + '-' + value.slice(9);" +
                        "  }" +
                        "  if (value.length > 12) {" +
                        "    value = value.slice(0, 12) + '-' + value.slice(12);" +
                        "  }" +
                        "  if (value.length > 15) {" +
                        "    value = value.slice(0, 15);" +
                        "  }" +
                        "  this.value = value;" +
                        "});"
        );

        firstName.addValueChangeListener(event -> {
            String input = event.getValue();
            if (!input.isEmpty()) {
                firstName.setValue(capitalizeFirstLetter(input));
            }
        });

        // Add a listener to capitalize the first letter of the last name
        lastName.addValueChangeListener(event -> {
            String input = event.getValue();
            if (!input.isEmpty()) {
                lastName.setValue(capitalizeFirstLetter(input));
            }
        });

        bloodType.setItems(BloodType.values());
        bloodType.setItemLabelGenerator(this::getBloodTypeLabel);

        isOrganDonor.setItems(Choice.values());
        isOrganDonor.setItemLabelGenerator(Choice::getDisplayString);

        add(formLayout, saveButton);

        saveButton.addClickListener(e -> registerPatient());

        binder.bindInstanceFields(this);
    }

    private String capitalizeFirstLetter(String input) {
        String[] parts = input.split(" ");
        StringBuilder capitalized = new StringBuilder();

        for (String part : parts) {
            if (!part.isEmpty()) {
                if (capitalized.length() > 0) {
                    capitalized.append(" ");
                }
                capitalized.append(part.substring(0, 1).toUpperCase()).append(part.substring(1).toLowerCase());
            }
        }

        return capitalized.toString();
    }


    private String getBloodTypeLabel(BloodType bloodType) {
        return bloodType.getDisplayString();
    }

    private void registerPatient() {
        PatientDto patientDto = new PatientDto();
        if(binder.writeBeanIfValid(patientDto)) {
            callRegisterPatientApi(patientDto);
        }
    }

    private void callRegisterPatientApi(PatientDto patientDto) {
        try {
            PatientDto response = restTemplate.postForObject(apiUrl, patientDto, PatientDto.class);
            // Handle the response, e.g., show a notification
            UI.getCurrent().access(() -> {
                Notification.show("Patient registered successfully", 3000, Notification.Position.MIDDLE);
            });
        } catch (Exception e) {
            // Handle errors, e.g., show an error notification
            UI.getCurrent().access(() -> {
                Notification.show("Failed to register patient: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
            });
        }
    }
}
