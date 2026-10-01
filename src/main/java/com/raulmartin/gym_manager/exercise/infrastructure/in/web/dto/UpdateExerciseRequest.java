package com.raulmartin.gym_manager.exercise.infrastructure.in.web.dto;

import com.raulmartin.gym_manager.exercise.domain.model.MuscleGroup;

import jakarta.validation.constraints.Size;

public record UpdateExerciseRequest(

    @Size(min = 2, max = 100)
    String name,

    @Size(max = 1000)
    String description,

    MuscleGroup muscleGroup
){}
