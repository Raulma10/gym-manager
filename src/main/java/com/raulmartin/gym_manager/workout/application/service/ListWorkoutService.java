package com.raulmartin.gym_manager.workout.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.workout.domain.model.Workout;
import com.raulmartin.gym_manager.workout.domain.port.in.ListWorkoutUseCase;
import com.raulmartin.gym_manager.workout.domain.port.out.WorkoutRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ListWorkoutService implements ListWorkoutUseCase{
    
    private final WorkoutRepositoryPort workoutRepositoryPort;

    @Override
    public List<Workout> listAll() {
        return workoutRepositoryPort.findAll();
    }
    
}
