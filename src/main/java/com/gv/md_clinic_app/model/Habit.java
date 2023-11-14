package com.gv.md_clinic_app.model;

import com.gv.md_clinic_app.model.enums.Choice;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Habit {
    private Choice smoking;
    private Choice alcohol;
    private Choice drugs;
    private Choice exercise;
    private Choice diet;
    private Choice isVegan;
    private Choice isVegetarian;
}
