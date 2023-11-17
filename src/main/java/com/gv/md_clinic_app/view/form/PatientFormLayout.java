package com.gv.md_clinic_app.view.form;

import com.gv.md_clinic_app.model.dto.PatientDto;
import com.gv.md_clinic_app.model.enums.*;
import com.gv.md_clinic_app.view.form.utils.PatientFormUtils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringComponent
@UIScope
public class PatientFormLayout extends VerticalLayout {
    private final RestTemplate restTemplate;
    private final PatientFormUtils patientFormUtils = new PatientFormUtils();
    private final String apiUrl = "http://localhost:8080/doctors/register-patient";

    //Basic Patient Information
    private final TextField firstName = new TextField("First Name", "Mary");
    private final TextField lastName = new TextField("Last Name", "Jane");
    private final TextField email = new TextField("Email", "samplmail@email.com");
    private final TextField phone = new TextField("Phone", "+(034) 555-555-555");
    private final DatePicker dob = new DatePicker("Date of Birth");
    private final TextField historyId = new TextField("History Number", "20201587");

    //Secondary Patient Information
    private final TextField street = new TextField("Street", "123 Main St");
    private final TextField city = new TextField("City", "Barcelona");
    private final ComboBox<States> state = new ComboBox<>("State");
    private final TextField zipCode = new TextField("zipCode", "08001");
    private final TextField emergencyContactFirstName = new TextField("First Name", "John");
    private final TextField emergencyContactLastName = new TextField("Last Name", "Doe");
    private final TextField emergencyContactPhone = new TextField("Phone", "+(034) 555-555-555");

    //Basic Patient MD Information
    private final ComboBox<BloodType> bloodType = new ComboBox<>("Blood Type");
    private final ComboBox<Choice> isOrganDonor = new ComboBox<>("Organ Donor");
    private final ComboBox<Gender> gender = new ComboBox<>("Gender");
    private final Button saveButton = new Button("Register");
    private final Binder<PatientDto> binder = new Binder<>(PatientDto.class);

    @Autowired
    public PatientFormLayout(RestTemplate restTemplate) {
        log.info("PatientFormLayout constructor called");
        this.restTemplate = restTemplate;

        // Create an accordion
        Accordion accordion = new Accordion();

        // Set the accordion's
        accordionSectionLayoutSetUp(accordion);

        // Create a FlexLayout for the button
        FlexLayout buttonLayout = new FlexLayout();
        buttonLayout.setSizeFull(); // Take the full size of the parent
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER); // Center horizontally
        buttonLayout.setAlignItems(FlexComponent.Alignment.CENTER); // Center vertically
        buttonLayout.add(saveButton);

        requiredFieldsSetUp();
        fieldsFeedback();
        fieldsValitations();
        comboBoxValuesSetUp();
        saveBtnConfigSetUp();

        // Create a FlexLayout for the form and accordion
        FlexLayout formLayout = new FlexLayout();
        formLayout.setFlexDirection(FlexLayout.FlexDirection.COLUMN); // Stack children vertically
        formLayout.setAlignItems(FlexComponent.Alignment.CENTER); // Center children horizontally

        // Add the accordion and button to the form layout
        formLayout.add(accordion, buttonLayout);

        // Add the form layout to the main layout
        add(formLayout);

        // Bind fields to the binder
        binder.bindInstanceFields(this);
    }
    private void accordionSectionLayoutSetUp(Accordion accordion) {
        // Basic Info Section
        basicPatienSectionSetUp(accordion);
        // Patient Secondary Info Section
        secondaryPatientSectionSetUp(accordion);
        // Basic Patient MD Info Section
        basicPatienMdSectionSetUp(accordion);
    }
    private void basicPatienMdSectionSetUp(Accordion accordion) {
        FormLayout basicPatientMdInfoLayout = new FormLayout();
        basicPatientMdInfoLayout.add(historyId, bloodType, isOrganDonor, gender);
        AccordionPanel basicPatientMdInfoPanel = accordion.add("Basic Medical Patient Information", basicPatientMdInfoLayout);
        basicPatientMdInfoPanel.setTooltipText("Basic medical patient information");
        basicPatientMdInfoPanel.addThemeVariants(DetailsVariant.FILLED);
    }
    private void secondaryPatientSectionSetUp(Accordion accordion) {
        FormLayout secondaryPatientInfoFormLayout = new FormLayout();
        secondaryPatientInfoFormLayout.add(street, city, state, zipCode, emergencyContactFirstName, emergencyContactLastName, emergencyContactPhone);
        AccordionPanel secondaryPatientInfoPanel = accordion.add("Secondary Patient Information", secondaryPatientInfoFormLayout);
        secondaryPatientInfoPanel.setTooltipText("Secondary patient basic information");
        secondaryPatientInfoPanel.addThemeVariants(DetailsVariant.FILLED);
    }
    private void basicPatienSectionSetUp(Accordion accordion) {
        FormLayout basicInfoFormLayout = new FormLayout();
        basicInfoFormLayout.add(firstName, lastName, email, phone, dob);
        AccordionPanel basicInfoPanel = accordion.add("Basic Patient Information", basicInfoFormLayout);
        basicInfoPanel.setTooltipText("Basic patient basic information");
        basicInfoPanel.addThemeVariants(DetailsVariant.FILLED);
    }
    private void saveBtnConfigSetUp() {
        saveButton.addClickListener(e -> registerPatient());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    }
    private void fieldsValitations() {
        // Validate the basic patient fields
        validateBasicPatientFields();

        // Validate the secondary patient fields
        validateSecondaryPatientFields();
    }
    private void validateSecondaryPatientFields() {

        patientFormUtils.fNameAndLNameValidators(emergencyContactFirstName, emergencyContactLastName);

        emergencyContactFirstName.addValueChangeListener(event -> {
                patientFormUtils.emailToLowerCase(email);
        });
    }
    private void validateBasicPatientFields() {
        patientFormUtils.fNameAndLNameValidators(firstName, lastName);
        patientFormUtils.dateFormatter(dob);

        phone.addValueChangeListener(event -> {
            if (!event.getValue().trim().isEmpty()) {
                patientFormUtils.phoneValidator(phone);
            }
        });
        email.addValueChangeListener(event -> {
            if (!event.getValue().trim().isEmpty()) {
                patientFormUtils.emailToLowerCase(email);
            }
        });
    }
    private void requiredFieldsSetUp() {
        //Basic Patient Information
        firstName.setRequiredIndicatorVisible(true);
        lastName.setRequiredIndicatorVisible(true);
        email.setRequiredIndicatorVisible(false);
        phone.setRequiredIndicatorVisible(false);
        dob.setRequiredIndicatorVisible(true);

        //Secondary Patient Information
        street.setRequiredIndicatorVisible(false);
        city.setRequiredIndicatorVisible(false);
        state.setRequiredIndicatorVisible(false);
        zipCode.setRequiredIndicatorVisible(false);
        emergencyContactFirstName.setRequiredIndicatorVisible(false);
        emergencyContactLastName.setRequiredIndicatorVisible(false);
        emergencyContactPhone.setRequiredIndicatorVisible(false);

        //Basic Patient MD Information
        bloodType.setRequiredIndicatorVisible(true);
        isOrganDonor.setRequiredIndicatorVisible(true);
        historyId.setRequiredIndicatorVisible(true);
        gender.setRequiredIndicatorVisible(true);
    }
    private void fieldsFeedback() {
        binder.forField(firstName)
                .asRequired("First name is required")
                .withValidator(new StringLengthValidator(
                        "First name must be between 2 and 50 characters", 2, 50))
                .withValidator(name -> name.matches(Regex.PERSON_NAME_PATTERN.getDisplayString()),
                        "First name can only contain letters, spaces, hyphens, and apostrophes")
                .bind(PatientDto::getFirstName, PatientDto::setFirstName);
        binder.forField(lastName)
                .asRequired("Last name is required")
                .withValidator(new StringLengthValidator(
                        "Last name must be between 2 and 50 characters", 2, 50))
                .withValidator(name -> name.matches(Regex.PERSON_NAME_PATTERN.getDisplayString()),
                        "Last name can only contain letters, spaces, hyphens, and apostrophes")
                .bind(PatientDto::getLastName, PatientDto::setLastName);
        binder.forField(email)
                .withValidator(emailStr -> emailStr.isEmpty() || !new EmailValidator("This doesn't look like a valid email address").apply(emailStr, null).isError(),
                        "This doesn't look like a valid email address")
                .withValidator(emailStr -> emailStr.length() <= 254, "Email must be less than 255 characters")
                .bind(PatientDto::getEmail, PatientDto::setEmail);

        binder.forField(phone)
                .withValidator(phoneNumber -> phoneNumber.isEmpty() || phoneNumber.matches(Regex.PHONE_NUMBER_CHECKER.getDisplayString()),
                        "Phone number must match the format +(XXX) XX-XXX-XXXX")
                .bind(PatientDto::getPhone, PatientDto::setPhone);

        binder.forField(gender)
                .asRequired("If gender is not known, please select 'Unknown'")
                .bind(PatientDto::getGender, PatientDto::setGender);
        binder.forField(bloodType)
                .asRequired("If blood type is not known, please select 'Unknown'")
                .bind(PatientDto::getBloodType, PatientDto::setBloodType);
        binder.forField(isOrganDonor)
                .asRequired("If organ donor info is not known, please select 'Unknown'")
                .bind(PatientDto::getIsOrganDonor, PatientDto::setIsOrganDonor);
        binder.forField(dob)
                .asRequired("Date of birth is required")
                .bind(PatientDto::getDob, PatientDto::setDob);
        binder.forField(historyId)
                .asRequired("History number is required")
                .withValidator(id -> id.matches(Regex.NUMERIC_PATTERN.getDisplayString()), "History number must only contain digits")
                .withValidator(new RegexpValidator(
                        "History number must only contain digits and be 4-10 characters long",
                        Regex.NUMERIC_4_TO_10_DIGITS_PATTERN.getDisplayString()))
                .bind(PatientDto::getHistoryId, PatientDto::setHistoryId);
    }
    private void comboBoxValuesSetUp() {
        // Set the values of the combo boxes
        bloodType.setItems(BloodType.values());
        bloodType.setItemLabelGenerator(BloodType::getDisplayString);

        // Set the Organ Donor values of the combo boxes
        isOrganDonor.setItems(Choice.values());
        isOrganDonor.setItemLabelGenerator(Choice::getDisplayString);

        // Set the Gender values of the combo boxes
        gender.setItems(Gender.values());
        gender.setItemLabelGenerator(Gender::getDisplayString);

        // Set the States values of the combo boxes
        state.setItems(States.values());
        state.setItemLabelGenerator(States::getDisplayString);
    }
    private void registerPatient() {
        log.info("Register patient button clicked");

        PatientDto patientDto = new PatientDto();
        if(binder.writeBeanIfValid(patientDto)) {
            callRegisterPatientApi(patientDto);
        }
    }
    private String sanitizeInput(String input) {
        return sanitizeString(input);
    }
    public String sanitizeString(String input) {
        return input.replaceAll(Regex.STRING_SANITIZER.getDisplayString(), ""); // This regex removes anything that looks like an HTML tag
    }
    private void callRegisterPatientApi(PatientDto patientDto) {
        log.info("Calling register patient api");

        patientDto.setFirstName(sanitizeInput(patientDto.getFirstName()));
        patientDto.setLastName(sanitizeInput(patientDto.getLastName()));
        patientDto.setEmail(sanitizeInput(patientDto.getEmail()));
        patientDto.setPhone(sanitizeInput(patientDto.getPhone()));

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
