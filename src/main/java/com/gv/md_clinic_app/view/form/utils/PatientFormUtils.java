package com.gv.md_clinic_app.view.form.utils;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextField;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * This class is used to validate the input fields of the patient form.
 */
@Slf4j
public class PatientFormUtils {
    /**
     * This method is used to capitalize the first letter of the input string.
     * @param input - the input string
     * @return capitalized - the capitalized string
     */
    public String capitalizeFirstLetter(@NotNull String input) {
        log.info("capitalizeFirstLetter called");

        String[] parts = input.split(" ");
        StringBuilder capitalized = new StringBuilder();

        for (String part : parts) {
            if (!part.isEmpty()) {
                if (!capitalized.isEmpty()) {
                    capitalized.append(" ");
                }
                capitalized.append(part.substring(0, 1).toUpperCase()).append(part.substring(1).toLowerCase());
            }
        }

        return capitalized.toString();
    }
    /**
     * This method is used to validate the first name and last name fields.
     * @param firstName - the first name field
     * @param lastName - the last name field
     */
    public void fNameAndLNameValidators(@NotNull TextField firstName, TextField lastName) {
        log.info("fNameAndLNameValidators called");
        if (!firstName.isEmpty()) firstName.setValue(capitalizeFirstLetter(firstName.getValue()));
        if(!lastName.isEmpty()) lastName.setValue(capitalizeFirstLetter(lastName.getValue()));
    }

    /**
     * This method is used to validate the email field.
     * @param field - the email field
     * @return true if the field is empty, false otherwise
     */
    public Boolean checkEmptyFields(@NotNull TextField field) {
        log.info("checkEmptyFields called");
        return field.isEmpty();
    }
    /**
     * This method is used to validate the email field.
     * @param email - the email field
     */
    public void emailToLowerCase(@NotNull TextField email) {
        log.info("emailToLowerCase called");
        if (!email.isEmpty()) email.setValue(email.getValue().toLowerCase());
    }

    /**
     * This method is used to validate the date of birth field.
     * @param dob - the date of birth field
     */
    public void dateFormatter(@NotNull DatePicker dob) {
        log.info("dateFormatter called");
        dob.setPlaceholder("dd-MM-yyyy");
        DatePicker.DatePickerI18n i18n = new DatePicker.DatePickerI18n()
                .setDateFormat("dd-MM-yyyy");

        dob.setI18n(i18n);
    }

    /**
     * This method is used to validate the phone number field.
     * @param phone - the phone number field
     */
    public void phoneValidator(@NotNull TextField phone) {
        log.info("phoneValidator called");

        phone.getElement().executeJs(
                "this.addEventListener('input', function() {" +
                        "  var value = this.value.replace(/[^\\d]/g, '');" +
                        "  if (value.length > 3) {" +
                        "    value = '+(' + value.slice(0, 3) + ') ' + value.slice(3);" +
                        "  }" +
                        "  if (value.length > 9) {" +
                        "    value = value.slice(0, 9) + '-' + value.slice(9);" +
                        "  }" +
                        "  if (value.length > 13) {" +
                        "    value = value.slice(0, 13) + '-' + value.slice(13);" +
                        "  }" +
                        "  if (value.length > 18) {" +
                        "    value = value.slice(0, 18);" +
                        "  }" +
                        "  this.value = value;" +
                        "});"
        );
    }
}
