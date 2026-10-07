package com.raulmartin.gym_manager.workout.domain.port.in;

import java.util.List;

import com.raulmartin.gym_manager.workout.domain.model.Workout;

public interface ListWorkoutUseCase {
    List<Workout> listAll();
}
