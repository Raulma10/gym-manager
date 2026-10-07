package com.raulmartin.gym_manager.workout.domain.port.in;

import java.util.List;
import java.util.UUID;

import com.raulmartin.gym_manager.workout.domain.model.Objective;
import com.raulmartin.gym_manager.workout.domain.model.Workout;

public interface UpdateWorkoutUseCase {
    Workout updateWorkout(UUID id, String name, String description, Objective objective);
}
