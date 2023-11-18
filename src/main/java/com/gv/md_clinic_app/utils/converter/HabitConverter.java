package com.gv.md_clinic_app.utils.converter;

import com.gv.md_clinic_app.model.Habit;
import com.gv.md_clinic_app.model.dto.HabitDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HabitConverter {
    public static Habit convertToEntity(HabitDto habitDto) {
        log.info("HabitConverter.convertToEntity() - habitDto: {}", habitDto);

        Habit habit = new Habit();

        habit.setDiet(habitDto.getDiet());
        habit.setAlcohol(habitDto.getAlcohol());
        habit.setCaffeine(habitDto.getCaffeine());
        habit.setDrugs(habitDto.getDrugs());
        habit.setExercise(habitDto.getExercise());
        habit.setIsOnMedication(habitDto.getIsOnMedication());
        habit.setIsVegan(habitDto.getIsVegan());
        habit.setIsVegetarian(habitDto.getIsVegetarian());
        habit.setSleep(habitDto.getSleep());
        habit.setSmoking(habitDto.getSmoking());
        habit.setStress(habitDto.getStress());

        return habit;
    }

    public static HabitDto convertToDto(Habit habit) {
        log.info("HabitConverter.convertToDto() - habit: {}", habit);

        HabitDto dto = new HabitDto();

        dto.setDiet(habit.getDiet());
        dto.setAlcohol(habit.getAlcohol());
        dto.setCaffeine(habit.getCaffeine());
        dto.setDrugs(habit.getDrugs());
        dto.setExercise(habit.getExercise());
        dto.setIsOnMedication(habit.getIsOnMedication());
        dto.setIsVegan(habit.getIsVegan());
        dto.setIsVegetarian(habit.getIsVegetarian());
        dto.setSleep(habit.getSleep());
        dto.setSmoking(habit.getSmoking());
        dto.setStress(habit.getStress());

        return dto;
    }
}
