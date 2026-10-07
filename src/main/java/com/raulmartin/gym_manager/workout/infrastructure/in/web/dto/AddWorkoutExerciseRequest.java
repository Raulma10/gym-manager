package com.raulmartin.gym_manager.workout.infrastructure.in.web.dto;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddWorkoutExerciseRequest(
    @NotNull
    UUID exerciseId,
 
    @NotNull
    @Min(1)
    Integer sets,
 
    @NotNull
    @Min(1)
    Integer reps,
 
    @NotNull
    @Min(0)
    Integer restSeconds,
 
    @NotNull
    @Min(0)
    Integer order
) {
    
}
