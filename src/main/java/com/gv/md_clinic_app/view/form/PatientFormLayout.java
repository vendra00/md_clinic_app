package com.gv.md_clinic_app.view.form;

import com.gv.md_clinic_app.model.dto.patient.PatientDto;
import com.gv.md_clinic_app.model.enums.*;
import com.gv.md_clinic_app.view.form.utils.PatientFormUtils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

/**
 * Represents the patient form layout
 */
@Slf4j
@SpringComponent
@UIScope
@CssImport("./styles/patient-form-styles.css")
public class PatientFormLayout extends VerticalLayout {
    //RestTemplate and related components
    private final RestTemplate restTemplate;
    private final VerticalLayout allergyFormsLayout = new VerticalLayout();
    private final PatientFormUtils patientFormUtils = new PatientFormUtils();
    private final String apiUrl = "http://localhost:8080/doctors/register-patient";

    //Basic Patient Information Components
    private final TextField firstName = new TextField("First Name", "Mary");
    private final TextField lastName = new TextField("Last Name", "Jane");
    private final TextField email = new TextField("Email", "samplmail@email.com");
    private final TextField phone = new TextField("Phone", "+(034) 555-555-555");
    private final DatePicker dob = new DatePicker("Date of Birth");
    private final TextField historyId = new TextField("History Number", "20201587");

    //Secondary Patient Information Components
    private final TextField street = new TextField("Street", "123 Main St");
    private final TextField city = new TextField("City", "Barcelona");
    private final ComboBox<States> state = new ComboBox<>("State");
    private final TextField zipCode = new TextField("zipCode", "08001");
    private final TextField emergencyContactFirstName = new TextField("First Name", "John");
    private final TextField emergencyContactLastName = new TextField("Last Name", "Doe");
    private final TextField emergencyContactPhone = new TextField("Phone", "+(034) 555-555-555");

    //Basic Patient MD Information Components
    private final ComboBox<BloodType> bloodType = new ComboBox<>("Blood Type");
    private final ComboBox<Choice> isOrganDonor = new ComboBox<>("Organ Donor");
    private final ComboBox<Gender> gender = new ComboBox<>("Gender");
    private final TextField height = new TextField("Height (cm)", "170");
    private final TextField weight = new TextField("Weight (kg)", "70");

    //Patient Habits Information Components
    private final ComboBox<Choice> smoking = new ComboBox<>("Smoking");
    private final ComboBox<Choice> alcohol = new ComboBox<>("Alcohol");
    private final ComboBox<Choice> drugs = new ComboBox<>("Drugs");
    private final ComboBox<Choice> exercise = new ComboBox<>("Exercise");
    private final ComboBox<Choice> diet = new ComboBox<>("Diet");
    private final ComboBox<Quality> sleep = new ComboBox<>("Sleep");
    private final ComboBox<Intensity> stress = new ComboBox<>("Stress");
    private final ComboBox<Choice> caffeine = new ComboBox<>("Caffeine");
    private final ComboBox<Choice> isVegan = new ComboBox<>("Vegan");
    private final ComboBox<Choice> isVegetarian = new ComboBox<>("Vegetarian");
    private final ComboBox<Choice> isOnMedication = new ComboBox<>("Medication");

    //Patient MD History Information Components
    private final ComboBox<Choice> isAllergic = new ComboBox<>("Has Known Allergies?");
    private final ComboBox<Choice> isIntolerance = new ComboBox<>("Has Known Intolerances?");
    private final Button addAllergyButton = new Button("Add Allergy");
    private final ComboBox<IntoleranceType> intoleranceType = new ComboBox<>("Intolerance Type");

    //Control buttons and related
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
        fieldsFeedbackBinder();
        fieldsValidations();
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

    //Layouts

    /**
     * Set up the accordion sections
     * @param accordion The accordion to set up
     */
    private void accordionSectionLayoutSetUp(Accordion accordion) {
        // Basic Info Section
        basicPatientSectionSetUp(accordion);
        // Patient Secondary Info Section
        secondaryPatientSectionSetUp(accordion);
        // Basic Patient MD Info Section
        basicPatientMdSectionSetUp(accordion);
        // Patient Habits Info Section
        patientHabitsSectionSetUp(accordion);
        // Patient MD History Info Section
        patientMDHistorySectionSetUp(accordion);
    }

    /**
     * Set up the patient habits section
     * @param accordion The accordion to set up
     */
    private void patientHabitsSectionSetUp(@NotNull Accordion accordion) {
        FormLayout patientHabitsInfoLayout = new FormLayout();
        patientHabitsInfoLayout.add(smoking, alcohol, drugs, exercise, diet, sleep, stress, caffeine, isVegan, isVegetarian, isOnMedication);
        AccordionPanel patientHabitsInfoPanel = accordion.add("Patient Habits Information", patientHabitsInfoLayout);
        patientHabitsInfoPanel.setTooltipText("Patient habits information");
        patientHabitsInfoPanel.addThemeVariants(DetailsVariant.FILLED);
    }

    /**
     * Set up the basic patient MD section
     * @param accordion The accordion to set up
     */
    private void basicPatientMdSectionSetUp(@NotNull Accordion accordion) {
        FormLayout basicPatientMdInfoLayout = new FormLayout();
        basicPatientMdInfoLayout.add(historyId, bloodType, isOrganDonor, gender, height, weight);
        AccordionPanel basicPatientMdInfoPanel = accordion.add("Basic Medical Patient Information", basicPatientMdInfoLayout);
        basicPatientMdInfoPanel.setTooltipText("Basic medical patient information");
        basicPatientMdInfoPanel.addThemeVariants(DetailsVariant.FILLED);
    }

    /**
     * Set up the secondary patient section
     * @param accordion The accordion to set up
     */
    private void secondaryPatientSectionSetUp(@NotNull Accordion accordion) {
        // Address section
        FormLayout addressLayout = new FormLayout();
        Span addressTitle = new Span("Patient Address");
        addressTitle.addClassName("section-title-secondary-patient-info"); // Use this class for styling in CSS
        addressLayout.add(street, city, state, zipCode);
        VerticalLayout addressSection = new VerticalLayout(addressTitle, addressLayout);

        // Emergency Contact section
        FormLayout emergencyContactLayout = new FormLayout();
        Span emergencyContactTitle = new Span("Patient Emergency Contact");
        emergencyContactTitle.addClassName("section-title-secondary-patient-info"); // Same class for consistent styling
        emergencyContactLayout.add(emergencyContactFirstName, emergencyContactLastName, emergencyContactPhone);
        VerticalLayout emergencyContactSection = new VerticalLayout(emergencyContactTitle, emergencyContactLayout);

        // Combine both sections in a single layout
        VerticalLayout secondaryPatientInfoFormLayout = new VerticalLayout();
        secondaryPatientInfoFormLayout.add(addressSection, emergencyContactSection);

        // Add the layout to the accordion panel
        AccordionPanel secondaryPatientInfoPanel = accordion.add("Secondary Patient Information", secondaryPatientInfoFormLayout);
        secondaryPatientInfoPanel.setTooltipText("Secondary patient basic information");
        secondaryPatientInfoPanel.addThemeVariants(DetailsVariant.FILLED);
    }

    /**
     * Set up the basic patient section
     * @param accordion The accordion to set up
     */
    private void basicPatientSectionSetUp(@NotNull Accordion accordion) {
        FormLayout basicInfoFormLayout = new FormLayout();
        basicInfoFormLayout.add(firstName, lastName, email, phone, dob);
        AccordionPanel basicInfoPanel = accordion.add("Basic Patient Information", basicInfoFormLayout);
        basicInfoPanel.setTooltipText("Basic patient basic information");
        basicInfoPanel.addThemeVariants(DetailsVariant.FILLED);
    }

    /**
     * Set up the patient MD history section
     * @param accordion The accordion to set up
     */
    private void patientMDHistorySectionSetUp(@NotNull Accordion accordion){
        // Address section
        FormLayout allergiesLayout = new FormLayout();
        Span allergiesTitle = new Span("Patient Allergies");
        allergiesTitle.addClassName("section-title-secondary-patient-info");
        allergiesLayout.add(isAllergic, addAllergyButton);
        VerticalLayout allergiesSection = new VerticalLayout(allergiesTitle, allergiesLayout);

        // Emergency Contact section
        FormLayout intolerancesLayout = new FormLayout();
        Span intolerancesTitle = new Span("Patient Intolerances");
        intolerancesTitle.addClassName("section-title-secondary-patient-info");
        intolerancesLayout.add(isIntolerance, intoleranceType);
        VerticalLayout intolerancesSection = new VerticalLayout(intolerancesTitle, intolerancesLayout);

        // Combine both sections in a single layout
        VerticalLayout patientMDHistoryInfoFormLayout = new VerticalLayout();
        patientMDHistoryInfoFormLayout.add(allergiesSection, intolerancesSection);

        // Add the layout to the accordion panel
        AccordionPanel patientMDHistoryInfoPanel = accordion.add("Patient Medical History Information", patientMDHistoryInfoFormLayout);
        patientMDHistoryInfoPanel.setTooltipText("Patient Medical History");
        patientMDHistoryInfoPanel.addThemeVariants(DetailsVariant.FILLED);

        // Add value change listener to isAllergic ComboBox
        isAllergic.addValueChangeListener(event -> {
            // Assuming 'Choice' enum has a value 'YES'
            boolean isAllergicSelected = Choice.YES.equals(event.getValue());
            addAllergyButton.setEnabled(isAllergicSelected);
            // If there are other fields related to allergies, enable/disable them here
            addAllergyButton.addClickListener(click -> openAllergyFormDialog());
            add(allergyFormsLayout);
        });

        // Add value change listener to isIntolerance ComboBox
        isIntolerance.addValueChangeListener(event -> {
            // Assuming 'Choice' enum has a value 'YES'
            boolean isIntolerantSelected = Choice.YES.equals(event.getValue());
            intoleranceType.setEnabled(isIntolerantSelected);
            // If there are other fields related to intolerances, enable/disable them here
        });

        // Initially disable the fields until an option is selected
        intoleranceType.setEnabled(false);
        addAllergyButton.setEnabled(false);
    }

    /**
     * Set up the save button
     */
    private void saveBtnConfigSetUp() {
        saveButton.addClickListener(e -> registerPatient());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    }

    //Validations

    /**
     * Validate the fields
     */
    private void fieldsValidations() {
        // Validate the basic patient fields
        validateBasicPatientFields();

        // Validate the secondary patient fields
        validateSecondaryPatientFields();
    }

    /**
     * Validate the secondary patient fields
     */
    private void validateSecondaryPatientFields() {
        patientFormUtils.fNameAndLNameValidators(emergencyContactFirstName, emergencyContactLastName);

        emergencyContactPhone.addBlurListener(event -> {
            if (!emergencyContactPhone.getValue().trim().isEmpty()) {
                patientFormUtils.phoneValidator(emergencyContactPhone);
            }
        });
    }

    /**
     * Validate the basic patient fields
     */
    private void validateBasicPatientFields() {
        patientFormUtils.fNameAndLNameValidators(firstName, lastName);
        patientFormUtils.dateFormatter(dob);

        // Convert to lowercase when the email field is blurred
        email.addBlurListener(event -> email.setValue(email.getValue().toLowerCase()));

        // Apply the phone mask when the phone field is blurred
        phone.addBlurListener(event -> {
            if (!phone.getValue().trim().isEmpty()) {
                patientFormUtils.phoneValidator(phone);
            }
        });
    }

    //Required Fields Setup

    /**
     * Set up the required fields
     */
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
        height.setRequiredIndicatorVisible(false);
        weight.setRequiredIndicatorVisible(false);

        //Patient Habits Information
        smoking.setRequiredIndicatorVisible(true);
        alcohol.setRequiredIndicatorVisible(true);
        drugs.setRequiredIndicatorVisible(true);
        exercise.setRequiredIndicatorVisible(true);
        diet.setRequiredIndicatorVisible(true);
        sleep.setRequiredIndicatorVisible(true);
        stress.setRequiredIndicatorVisible(true);
        caffeine.setRequiredIndicatorVisible(true);
        isVegan.setRequiredIndicatorVisible(true);
        isVegetarian.setRequiredIndicatorVisible(true);
        isOnMedication.setRequiredIndicatorVisible(true);

        //Patient MD History Information
        isAllergic.setRequiredIndicatorVisible(true);
        isIntolerance.setRequiredIndicatorVisible(true);
        intoleranceType.setRequiredIndicatorVisible(false);
    }

    /**
     * Set up the combo boxes with the values from the enums
     * @param comboBox The combo box to set up
     * @param values The values to set up the combo box with
     * @param displayStringGenerator The function to generate the display string for the combo box
     * @param <T> The type of the enum
     */
    private <T extends Enum<T>> void setupComboBox(ComboBox<T> comboBox, T[] values, Function<T, String> displayStringGenerator) {
        comboBox.setItems(values);
        comboBox.setItemLabelGenerator(displayStringGenerator::apply);
    }

    /**
     * Set up the combo boxes with the values from the enums
     */
    private void comboBoxValuesSetUp() {
        setupComboBox(bloodType, BloodType.values(), BloodType::getDisplayString);
        setupComboBox(isOrganDonor, Choice.values(), Choice::getDisplayString);
        setupComboBox(gender, Gender.values(), Gender::getDisplayString);
        setupComboBox(state, States.values(), States::getDisplayString);
        setupComboBox(smoking, Choice.values(), Choice::getDisplayString);
        setupComboBox(alcohol, Choice.values(), Choice::getDisplayString);
        setupComboBox(drugs, Choice.values(), Choice::getDisplayString);
        setupComboBox(exercise, Choice.values(), Choice::getDisplayString);
        setupComboBox(diet, Choice.values(), Choice::getDisplayString);
        setupComboBox(sleep, Quality.values(), Quality::getDisplayString);
        setupComboBox(stress, Intensity.values(), Intensity::getDisplayString);
        setupComboBox(caffeine, Choice.values(), Choice::getDisplayString);
        setupComboBox(isVegan, Choice.values(), Choice::getDisplayString);
        setupComboBox(isVegetarian, Choice.values(), Choice::getDisplayString);
        setupComboBox(isOnMedication, Choice.values(), Choice::getDisplayString);
        setupComboBox(isAllergic, Choice.values(), Choice::getDisplayString);
        setupComboBox(isIntolerance, Choice.values(), Choice::getDisplayString);
        setupComboBox(intoleranceType, IntoleranceType.values(), IntoleranceType::getDisplayString);
    }

    /**
     * Bind the fields to the binder
     */
    private void fieldsFeedbackBinder() {
        //Basic Patient Information
        basicPatientInfoBinder();

        //Basic Patient MD Information
        basicPatientMdInfoBinder();

        //Secondary patient information
        secondaryPatientInfoBinder();

        //Patient Habits Information
        patientHabitsBinder();
    }

    /**
     * Bind the basic patient information fields to the binder
     */
    private void basicPatientInfoBinder() {
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
    }

    /**
     * Bind the basic patient MD information fields to the binder
     */
    private void basicPatientMdInfoBinder() {
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
        double someMaxHeight = 300;
        binder.forField(height)
                .withConverter(new StringToDoubleConverter("Must enter a number"))
                .withValidator(heightValue -> heightValue == null || heightValue >= 0, "Height must be a positive number")
                .withValidator(heightValue -> heightValue == null || heightValue <= someMaxHeight, "Height must be less than " + someMaxHeight)
                .bind(PatientDto::getHeight, PatientDto::setHeight);
        double someMaxWeight=600;
        binder.forField(weight)
                .withConverter(new StringToDoubleConverter("Must enter a number"))
                .withValidator(weightValue -> weightValue == null || weightValue >= 0, "Weight must be a positive number")
                .withValidator(weightValue -> weightValue == null || weightValue <= someMaxWeight, "Weight must be less than " + someMaxWeight)
                .bind(PatientDto::getWeight, PatientDto::setWeight);
    }

    /**
     * Bind the secondary patient information fields to the binder
     */
    private void secondaryPatientInfoBinder() {
        //Address Section
        binder.forField(street)
                .withValidator(streetStr -> streetStr.length() <= 50, "Street must be less than 50 characters")
                .bind(patientDto -> patientDto.getAddress().getStreet(), (patientDto, streetValue) -> patientDto.getAddress().setStreet(streetValue));
        binder.forField(city)
                .withValidator(cityStr -> cityStr.length() <= 50, "City must be less than 50 characters")
                .bind(patientDto -> patientDto.getAddress().getCity(), (patientDto, cityValue) -> patientDto.getAddress().setCity(cityValue));
        binder.forField(zipCode)
                .withValidator(zipCodeStr -> zipCodeStr.isEmpty() || zipCodeStr.matches(Regex.ZIP_CODE_PATTERN.getDisplayString()),
                        "Zip code must match the format XXXXX or XXXXX-XXXX")
                .bind(patientDto -> patientDto.getAddress().getZipCode(), (patientDto, zipCodeValue) -> patientDto.getAddress().setZipCode(zipCodeValue));
        binder.forField(state)
                .bind(patientDto -> patientDto.getAddress().getState(), (patientDto, stateValue) -> patientDto.getAddress().setState(stateValue));

        //Emergency Contact Section
        binder.forField(emergencyContactPhone)
                .withValidator(phoneNumber -> phoneNumber.isEmpty() || phoneNumber.matches(Regex.PHONE_NUMBER_CHECKER.getDisplayString()),
                        "Phone number must match the format +(XXX) XX-XXX-XXXX")
                .bind(patientDto -> patientDto.getEmergencyContact().getPhone(), (patientDto, phoneValue) -> patientDto.getEmergencyContact().setPhone(phoneValue));
        binder.forField(emergencyContactFirstName)
                .withValidator(new StringLengthValidator(
                        "First name must be between 2 and 50 characters", 2, 50))
                .withValidator(name -> name.matches(Regex.PERSON_NAME_PATTERN.getDisplayString()),
                        "First name can only contain letters, spaces, hyphens, and apostrophes")
                .bind(patientDto -> patientDto.getEmergencyContact().getFirstName(), (patientDto, firstNameValue) -> patientDto.getEmergencyContact().setFirstName(firstNameValue));
        binder.forField(emergencyContactLastName)
                .withValidator(new StringLengthValidator(
                        "Last name must be between 2 and 50 characters", 2, 50))
                .withValidator(name -> name.matches(Regex.PERSON_NAME_PATTERN.getDisplayString()),
                        "Last name can only contain letters, spaces, hyphens, and apostrophes")
                .bind(patientDto -> patientDto.getEmergencyContact().getLastName(), (patientDto, lastNameValue) -> patientDto.getEmergencyContact().setLastName(lastNameValue));
    }

    /**
     * Bind the patient habits information fields to the binder
     */
    private void patientHabitsBinder() {
        binder.forField(smoking)
                .asRequired("If smoking habit is not known, please select 'Unknown'")
                .bind(patientDto -> patientDto.getHabit().getSmoking(), (patientDto, smokingValue) -> patientDto.getHabit().setSmoking(smokingValue));
        binder.forField(alcohol)
                .asRequired("If alcohol habit is not known, please select 'Unknown'")
                .bind(patientDto -> patientDto.getHabit().getAlcohol(), (patientDto, alcoholValue) -> patientDto.getHabit().setAlcohol(alcoholValue));
        binder.forField(drugs)
                .asRequired("If drugs habit is not known, please select 'Unknown'")
                .bind(patientDto -> patientDto.getHabit().getDrugs(), (patientDto, drugsValue) -> patientDto.getHabit().setDrugs(drugsValue));
        binder.forField(exercise)
                .asRequired("If exercise habit is not known, please select 'Unknown'")
                .bind(patientDto -> patientDto.getHabit().getExercise(), (patientDto, exerciseValue) -> patientDto.getHabit().setExercise(exerciseValue));
        binder.forField(diet)
                .asRequired("If diet habit is not known, please select 'Unknown'")
                .bind(patientDto -> patientDto.getHabit().getDiet(), (patientDto, dietValue) -> patientDto.getHabit().setDiet(dietValue));
        binder.forField(sleep)
                .asRequired("If sleep habit is not known, please select 'Unknown'")
                .bind(patientDto -> patientDto.getHabit().getSleep(), (patientDto, sleepValue) -> patientDto.getHabit().setSleep(sleepValue));
        binder.forField(stress)
                .asRequired("If stress habit is not known, please select 'Unknown'")
                .bind(patientDto -> patientDto.getHabit().getStress(), (patientDto, stressValue) -> patientDto.getHabit().setStress(stressValue));
        binder.forField(caffeine)
                .asRequired("If caffeine habit is not known, please select 'Unknown'")
                .bind(patientDto -> patientDto.getHabit().getCaffeine(), (patientDto, caffeineValue) -> patientDto.getHabit().setCaffeine(caffeineValue));
        binder.forField(isVegan)
                .asRequired("If vegan habit is not known, please select 'Unknown'")
                .bind(patientDto -> patientDto.getHabit().getIsVegan(), (patientDto, isVeganValue) -> patientDto.getHabit().setIsVegan(isVeganValue));
        binder.forField(isVegetarian)
                .asRequired("If vegetarian habit is not known, please select 'Unknown'")
                .bind(patientDto -> patientDto.getHabit().getIsVegetarian(), (patientDto, isVegetarianValue) -> patientDto.getHabit().setIsVegetarian(isVegetarianValue));
        binder.forField(isOnMedication)
                .asRequired("If medication habit is not known, please select 'Unknown'")
                .bind(patientDto -> patientDto.getHabit().getIsOnMedication(), (patientDto, isOnMedicationValue) -> patientDto.getHabit().setIsOnMedication(isOnMedicationValue));
    }

    /**
     * Bind the patient MD history information fields to the binder
     */
    private void patientMDHistoryBinder() {

    }

    /**
     * Register the patient
     */
    private void registerPatient() {
        log.info("Register patient button clicked");

        PatientDto patientDto = new PatientDto();
        if(binder.writeBeanIfValid(patientDto)) {
            callRegisterPatientApi(patientDto);
        }
    }

    /**
     * Sanitize the input
     * @param input The input to sanitize
     * @return The sanitized input
     */
    private String sanitizeInput(String input) {
        return sanitizeString(input);
    }

    /**
     * Sanitize the string
     * @param input The string to sanitize
     * @return The sanitized string
     */
    public String sanitizeString(@NotNull String input) {
        return input.replaceAll(Regex.STRING_SANITIZER.getDisplayString(), ""); // This regex removes anything that looks like an HTML tag
    }

    /**
     * Call the register patient API
     * @param patientDto The patient DTO to send to the API
     */
    private void callRegisterPatientApi(@NotNull PatientDto patientDto) {
        log.info("Calling register patient api");

        //Basic Patient Information
        patientDto.setFirstName(sanitizeInput(patientDto.getFirstName()));
        patientDto.setLastName(sanitizeInput(patientDto.getLastName()));
        patientDto.setEmail(sanitizeInput(patientDto.getEmail()));
        patientDto.setPhone(sanitizeInput(patientDto.getPhone()));
        patientDto.setDob(patientDto.getDob());

        //Secondary Patient Information
        patientDto.getAddress().setStreet(sanitizeInput(patientDto.getAddress().getStreet()));
        patientDto.getAddress().setCity(sanitizeInput(patientDto.getAddress().getCity()));
        patientDto.getAddress().setState(patientDto.getAddress().getState());
        patientDto.getAddress().setZipCode(sanitizeInput(patientDto.getAddress().getZipCode()));
        patientDto.getEmergencyContact().setPhone(sanitizeInput(patientDto.getEmergencyContact().getPhone()));
        patientDto.getEmergencyContact().setFirstName(sanitizeInput(patientDto.getEmergencyContact().getFirstName()));
        patientDto.getEmergencyContact().setLastName(sanitizeInput(patientDto.getEmergencyContact().getLastName()));

        //Basic Patient MD Information
        patientDto.setHistoryId(sanitizeInput(patientDto.getHistoryId()));
        patientDto.setBloodType(patientDto.getBloodType());
        patientDto.setIsOrganDonor(patientDto.getIsOrganDonor());
        patientDto.setGender(patientDto.getGender());
        patientDto.setHeight(patientDto.getHeight());
        patientDto.setWeight(patientDto.getWeight());

        try {
            PatientDto response = restTemplate.postForObject(apiUrl, patientDto, PatientDto.class);
            // Handle the response, e.g., show a notification
            UI.getCurrent().access(() -> Notification.show("Patient registered successfully", 3000, Notification.Position.MIDDLE));
        } catch (Exception e) {
            // Handle errors, e.g., show an error notification
            UI.getCurrent().access(() -> Notification.show("Failed to register patient: " + e.getMessage(), 3000, Notification.Position.MIDDLE));
        }
    }

    //Supporting Methods and Forms

    /**
     * Open the allergy form dialog
     */
    private void openAllergyFormDialog() {
        Dialog allergyDialog = new Dialog();

        H3 dialogTitle = new H3("Add Allergy Details");

        // Layout for the header, which contains the title and the close button
        HorizontalLayout headerLayout = new HorizontalLayout();
        headerLayout.addClassName("dialog-header");
        headerLayout.setWidthFull();
        headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        headerLayout.add(dialogTitle);

        AllergyForm allergyForm = new AllergyForm(allergyDialog);

        // Add the header and the form to the dialog
        allergyDialog.add(headerLayout, allergyForm);
        allergyDialog.setDraggable(true);
        allergyDialog.open();
    }

}
