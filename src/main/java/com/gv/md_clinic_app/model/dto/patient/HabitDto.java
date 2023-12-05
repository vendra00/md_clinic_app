package com.gv.md_clinic_app.model.dto.patient;

import com.gv.md_clinic_app.model.enums.Choice;
import com.gv.md_clinic_app.model.enums.Intensity;
import com.gv.md_clinic_app.model.enums.Quality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object for Habit entity.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HabitDto {
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
