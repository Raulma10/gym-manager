package com.raulmartin.gym_manager.workout.infrastructure.in.web.dto;


import com.raulmartin.gym_manager.exercise.infrastructure.in.web.dto.ExerciseResponse;

public record WorkoutExerciseResponse(
    ExerciseResponse exercise,
    int sets,
    int reps,
    int restSeconds,
    int exerciseOrder
) {
    
}
