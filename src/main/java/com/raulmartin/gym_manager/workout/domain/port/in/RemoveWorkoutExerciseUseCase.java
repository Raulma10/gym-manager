package com.raulmartin.gym_manager.workout.domain.port.in;

import java.util.UUID;

import com.raulmartin.gym_manager.workout.domain.model.Workout;

public interface RemoveWorkoutExerciseUseCase {
    Workout removeExercise(UUID workoutId, UUID exerciseId);
}
