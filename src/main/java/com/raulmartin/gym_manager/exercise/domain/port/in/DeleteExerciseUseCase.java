package com.raulmartin.gym_manager.exercise.domain.port.in;

import java.util.UUID;

public interface DeleteExerciseUseCase {
    void deleteExercise(UUID id);
}
