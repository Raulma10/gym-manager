package com.raulmartin.gym_manager.workout.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.workout.domain.model.Objective;
import com.raulmartin.gym_manager.workout.domain.model.Workout;
import com.raulmartin.gym_manager.workout.domain.port.in.UpdateWorkoutUseCase;
import com.raulmartin.gym_manager.workout.domain.port.out.WorkoutRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateWorkoutService implements UpdateWorkoutUseCase {

    private final WorkoutRepositoryPort workoutRepositoryPort;

    @Override
    public Workout updateWorkout(UUID id, String name, String description, Objective objective) {
        Workout existing = workoutRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Workout not found: " + id));
 
        Workout updated = existing.update(name, description, objective);
        return workoutRepositoryPort.save(updated);
    }
}