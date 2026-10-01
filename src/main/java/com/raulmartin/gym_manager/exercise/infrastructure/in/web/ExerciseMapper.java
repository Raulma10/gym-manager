package com.raulmartin.gym_manager.exercise.infrastructure.in.web;

import org.mapstruct.Mapper;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;
import com.raulmartin.gym_manager.exercise.infrastructure.in.web.dto.ExerciseResponse;


@Mapper(componentModel = "spring")
public interface ExerciseMapper {
    ExerciseResponse toResponse(Exercise exercise);

}
