package com.raulmartin.gym_manager.workout.infrastructure.in.web.dto;

import java.util.List;
import java.util.UUID;

import com.raulmartin.gym_manager.workout.domain.model.Objective;

public record WorkoutResponse(
    UUID id,
    String name,
    String description,
    Objective objective,
    List<WorkoutExerciseResponse> workoutExercises
) {
    
}
