package com.raulmartin.gym_manager.workout.infrastructure.in.web.dto;

import java.util.UUID;

import jakarta.validation.constraints.Min;

public record WorkoutExerciseRequest(
    
    UUID exerciseId,
 
    @Min(1)
    Integer sets,
 
    @Min(1)
    Integer reps,
 
    @Min(0)
    Integer restSeconds,
 
    @Min(0)
    Integer order
){
}
