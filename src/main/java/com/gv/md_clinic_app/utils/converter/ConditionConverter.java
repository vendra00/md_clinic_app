package com.gv.md_clinic_app.utils.converter;

import com.gv.md_clinic_app.model.Condition;
import com.gv.md_clinic_app.model.dto.ConditionDto;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ConditionConverter {
    public static Set<Condition> convertToEntity(Set<ConditionDto> conditionDtos) {
        log.info("Converting ConditionDto to Condition");

        Set<Condition> conditions = new HashSet<>();

        for (ConditionDto dto : conditionDtos) {
            Condition condition = new Condition();
            condition.setName(dto.getName());
            condition.setDescription(dto.getDescription());
            conditions.add(condition);
        }
        return conditions;
    }

    public static Set<ConditionDto> convertToDto(Set<Condition> conditions) {
        log.info("Converting Condition to ConditionDto");

        Set<ConditionDto> dtos = new HashSet<>();

        for (Condition condition : conditions) {
            ConditionDto dto = new ConditionDto();
            dto.setName(condition.getName());
            dto.setDescription(condition.getDescription());
            dtos.add(dto);
        }
        return dtos;
    }
}
