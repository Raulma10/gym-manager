package com.raulmartin.gym_manager.workout.domain.port.in;

import java.util.Optional;
import java.util.UUID;

import com.raulmartin.gym_manager.workout.domain.model.Workout;

public interface FindWorkoutByIdUseCase {
    Optional<Workout> findById(UUID id);
}
