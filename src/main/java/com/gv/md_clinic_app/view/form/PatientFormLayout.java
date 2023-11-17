package com.gv.md_clinic_app.view.form;

import com.gv.md_clinic_app.model.dto.PatientDto;
import com.gv.md_clinic_app.model.enums.BloodType;
import com.gv.md_clinic_app.model.enums.Choice;
import com.gv.md_clinic_app.model.enums.Gender;
import com.gv.md_clinic_app.model.enums.Regex;
import com.gv.md_clinic_app.view.form.utils.PatientFormUtils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
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
    private final ComboBox<BloodType> bloodType = new ComboBox<>("Blood Type");
    private final ComboBox<Choice> isOrganDonor = new ComboBox<>("Organ Donor");
    private final ComboBox<Gender> gender = new ComboBox<>("Gender");
    private final TextField emergencyContactName = new TextField("Emergency Contact Name");
    //private final TextField address = new TextField("Address");
    private final Button saveButton = new Button("Register");
    private final Binder<PatientDto> binder = new Binder<>(PatientDto.class);

    @Autowired
    public PatientFormLayout(RestTemplate restTemplate) {
        log.info("PatientFormLayout constructor called");

        this.restTemplate = restTemplate;

        // Create an accordion
        Accordion accordion = new Accordion();

        // Basic Info Section
        FormLayout basicInfoFormLayout = new FormLayout();
        basicInfoFormLayout.add(firstName, lastName, email, phone, dob);
        AccordionPanel basicInfoPanel = accordion.add("Basic Info", basicInfoFormLayout);
        basicInfoPanel.addThemeVariants(DetailsVariant.SMALL); // Apply the small variant

        // Secondary Info Section
        FormLayout secondaryInfoFormLayout = new FormLayout();
        secondaryInfoFormLayout.add(historyId, bloodType, isOrganDonor, gender, emergencyContactName);
        AccordionPanel secondaryInfoPanel = accordion.add("Secondary Info", secondaryInfoFormLayout);
        secondaryInfoPanel.addThemeVariants(DetailsVariant.SMALL); // Apply the small variant

        requiredFieldsSetUp();
        fieldsFeedback();
        fieldsValitations();
        comboBoxValuesSetUp();

        // Add components to the layout
        add(accordion, saveButton); // Add the accordion and save button to the PatientFormLayout

        // Configure the save button
        saveButton.addClickListener(e -> registerPatient());

        // Bind fields to the binder
        binder.bindInstanceFields(this);
    }
    private void fieldsValitations() {
        patientFormUtils.fNameAndLNameValidators(firstName, lastName);
        patientFormUtils.dateFormatter(dob);
        patientFormUtils.phoneValidator(phone);
        patientFormUtils.emailToLowerCase(email);
    }
    private void requiredFieldsSetUp() {
        firstName.setRequiredIndicatorVisible(true);
        lastName.setRequiredIndicatorVisible(true);
        email.setRequiredIndicatorVisible(true);
        phone.setRequiredIndicatorVisible(true);
        dob.setRequiredIndicatorVisible(true);
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
                .asRequired("Email is required")
                .withValidator(new EmailValidator("This doesn't look like a valid email address"))
                .withValidator(email -> email.length() <= 254, "Email must be less than 255 characters")
                .bind(PatientDto::getEmail, PatientDto::setEmail);
        binder.forField(phone)
                .asRequired("Phone is required")
                .withValidator(phoneNumber -> phoneNumber.matches(Regex.PHONE_NUMBER_CHECKER.getDisplayString()),
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
        bloodType.setItems(BloodType.values());
        bloodType.setItemLabelGenerator(BloodType::getDisplayString);

        isOrganDonor.setItems(Choice.values());
        isOrganDonor.setItemLabelGenerator(Choice::getDisplayString);

        gender.setItems(Gender.values());
        gender.setItemLabelGenerator(Gender::getDisplayString);
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
