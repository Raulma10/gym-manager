package com.raulmartin.gym_manager.workout.infrastructure.in.web.dto;

import java.util.List;

import com.raulmartin.gym_manager.workout.domain.model.Objective;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateWorkoutRequest(
    @NotBlank
    @NotNull
    @Size(min = 2, max = 100)
    String name,
 
    @NotBlank
    @NotNull
    @Size(max = 1000)
    String description,
 
    @NotNull
    Objective objective,
 
    @NotEmpty
    @Valid
    List<WorkoutExerciseRequest> exercises
) {
    
}
