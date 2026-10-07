package com.raulmartin.gym_manager.workout.domain.port.in;

import java.util.UUID;

public interface DeleteWorkoutUseCase {
    void deleteById(UUID id);
}
