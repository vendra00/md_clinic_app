package com.gv.md_clinic_app.model.patient;

import com.gv.md_clinic_app.model.enums.Choice;
import com.gv.md_clinic_app.model.enums.Intensity;
import com.gv.md_clinic_app.model.enums.Quality;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Habit {
    private Choice smoking;
    private Choice alcohol;
    private Choice drugs;
    private Choice exercise;
    private Quality sleep;
    private Intensity stress;
    private Choice caffeine;
    private Choice diet;
    private Choice isVegan;
    private Choice isVegetarian;
    private Choice isOnMedication;
}
