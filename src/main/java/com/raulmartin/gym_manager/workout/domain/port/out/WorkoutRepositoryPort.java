package com.raulmartin.gym_manager.workout.domain.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.raulmartin.gym_manager.workout.domain.model.Objective;
import com.raulmartin.gym_manager.workout.domain.model.Workout;

public interface WorkoutRepositoryPort {
    Workout save(Workout workout);
    Optional<Workout> findById(UUID id);
    List<Workout> findAll();
    Page<Workout> searchWorkout(String name, Objective objective, Pageable pageable);
    void deleteById(UUID id);
}
