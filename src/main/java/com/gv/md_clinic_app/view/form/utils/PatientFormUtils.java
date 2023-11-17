package com.gv.md_clinic_app.view.form.utils;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatientFormUtils {
    public String capitalizeFirstLetter(String input) {
        log.info("capitalizeFirstLetter called");

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
    public void fNameAndLNameValidators(TextField firstName, TextField lastName) {
        log.info("fNameAndLNameValidators called");

        firstName.addValueChangeListener(event -> {
            String input = event.getValue();
            if (!input.isEmpty()) {
                firstName.setValue(capitalizeFirstLetter(input));
            }
        });

        lastName.addValueChangeListener(event -> {
            String input = event.getValue();
            if (!input.isEmpty()) {
                lastName.setValue(capitalizeFirstLetter(input));
            }
        });
    }
    public void emailToLowerCase(TextField email) {
        log.info("emailToLowerCase called");

        email.addValueChangeListener(event -> {
            String lowerCaseValue = event.getValue().toLowerCase();
            email.setValue(lowerCaseValue);
        });
    }
    public void dateFormatter(DatePicker dob) {
        log.info("dateFormatter called");
        dob.setPlaceholder("dd-MM-yyyy");
        DatePicker.DatePickerI18n i18n = new DatePicker.DatePickerI18n()
                .setDateFormat("dd-MM-yyyy");

        dob.setI18n(i18n);
    }
    public void phoneValidator(TextField phone) {
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
