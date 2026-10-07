package com.raulmartin.gym_manager.workout.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.workout.domain.model.Objective;
import com.raulmartin.gym_manager.workout.domain.model.Workout;
import com.raulmartin.gym_manager.workout.domain.port.in.SearchWorkoutUseCase;
import com.raulmartin.gym_manager.workout.domain.port.out.WorkoutRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchWorkoutService implements SearchWorkoutUseCase{
    
    private final WorkoutRepositoryPort workoutRepositoryPort;

    @Override
    public Page<Workout> searchWorkout(String name, Objective objective, Pageable pageable) {
        return workoutRepositoryPort.searchWorkout(name, objective, pageable);
    }
    
}
