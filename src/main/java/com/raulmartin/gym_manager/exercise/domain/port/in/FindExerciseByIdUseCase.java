package com.raulmartin.gym_manager.exercise.domain.port.in;

import java.util.Optional;
import java.util.UUID;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;

public interface FindExerciseByIdUseCase {
    Optional<Exercise> findById(UUID id);
}
