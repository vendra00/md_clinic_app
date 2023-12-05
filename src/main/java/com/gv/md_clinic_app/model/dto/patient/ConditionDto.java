package com.gv.md_clinic_app.model.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object for Condition entity.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConditionDto {
    private String name;
    private String description;

}
