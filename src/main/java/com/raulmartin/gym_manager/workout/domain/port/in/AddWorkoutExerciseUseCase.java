package com.raulmartin.gym_manager.workout.domain.port.in;

import java.util.UUID;

import com.raulmartin.gym_manager.workout.domain.model.Workout;

public interface AddWorkoutExerciseUseCase {
    Workout addExercise(UUID workoutId, UUID exerciseId, int sets, int reps, int restSeconds, int order);
}
