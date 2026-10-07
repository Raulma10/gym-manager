package com.raulmartin.gym_manager.workout.domain.port.in;

import java.util.List;

import com.raulmartin.gym_manager.workout.domain.model.Objective;
import com.raulmartin.gym_manager.workout.domain.model.Workout;

public interface CreateWorkoutUseCase {
    Workout createWorkout(String name, String description, Objective objective, List<ExerciseSelection> exercises);
}
