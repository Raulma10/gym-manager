package com.raulmartin.gym_manager.exercise.domain.port.in;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;
import com.raulmartin.gym_manager.exercise.domain.model.MuscleGroup;

public interface CreateExerciseUseCase {
    Exercise createExercise(String name, String description, MuscleGroup muscleGroup);
}
