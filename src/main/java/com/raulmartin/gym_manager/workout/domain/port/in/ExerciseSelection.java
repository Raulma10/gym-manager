package com.raulmartin.gym_manager.workout.domain.port.in;

import java.util.UUID;

public record ExerciseSelection(
    UUID exerciseId,
    int sets,
    int reps,
    int restSeconds,
    int order
) {
    
}
