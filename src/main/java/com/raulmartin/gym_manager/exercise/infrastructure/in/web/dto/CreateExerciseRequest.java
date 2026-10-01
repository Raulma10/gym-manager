package com.raulmartin.gym_manager.exercise.infrastructure.in.web.dto;

import com.raulmartin.gym_manager.exercise.domain.model.MuscleGroup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateExerciseRequest(
    @NotBlank
    @Size(min = 2, max = 100)
    String name,

    @NotBlank
    @Size(max = 1000)
    String description,

    @NotNull
    MuscleGroup muscleGroup
){}
