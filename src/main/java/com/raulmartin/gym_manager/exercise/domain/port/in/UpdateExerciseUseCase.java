package com.raulmartin.gym_manager.exercise.domain.port.in;

import java.util.UUID;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;
import com.raulmartin.gym_manager.exercise.domain.model.MuscleGroup;

public interface UpdateExerciseUseCase {
    Exercise updateExercise(UUID id, String name, String description, MuscleGroup muscleGroup);
}
