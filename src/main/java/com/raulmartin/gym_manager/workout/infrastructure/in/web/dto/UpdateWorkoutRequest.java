package com.raulmartin.gym_manager.workout.infrastructure.in.web.dto;

import java.util.List;

import com.raulmartin.gym_manager.workout.domain.model.Objective;

import jakarta.validation.constraints.Size;

public record UpdateWorkoutRequest(

    @Size(max = 100)
    String name,
 
    @Size(max = 1000)
    String description,
 
    Objective objective,
 
    
    List<WorkoutExerciseRequest> exercises
) {
    
}
