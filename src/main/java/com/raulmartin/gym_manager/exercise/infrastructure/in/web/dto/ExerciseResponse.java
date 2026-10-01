package com.raulmartin.gym_manager.exercise.infrastructure.in.web.dto;

import java.util.UUID;

import com.raulmartin.gym_manager.exercise.domain.model.MuscleGroup;

public record ExerciseResponse(
    UUID id,
    String name,
    MuscleGroup muscleGroup
) {}
