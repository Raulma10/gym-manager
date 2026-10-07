package com.raulmartin.gym_manager.workout.infrastructure.in.web.dto;


public record UpdateWorkoutExerciseRequest(
    Integer sets,
 
    Integer reps,
 
    Integer restSeconds,
 
    Integer order
) {
    
}
