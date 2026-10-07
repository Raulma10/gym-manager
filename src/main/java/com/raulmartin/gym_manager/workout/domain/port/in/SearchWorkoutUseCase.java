package com.raulmartin.gym_manager.workout.domain.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.raulmartin.gym_manager.workout.domain.model.Objective;
import com.raulmartin.gym_manager.workout.domain.model.Workout;

public interface SearchWorkoutUseCase {
    Page<Workout> searchWorkout(String name, Objective objective, Pageable pageable);
}
