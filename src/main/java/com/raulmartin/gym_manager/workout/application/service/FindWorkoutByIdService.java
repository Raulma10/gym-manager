package com.raulmartin.gym_manager.workout.application.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.workout.domain.model.Workout;
import com.raulmartin.gym_manager.workout.domain.port.in.FindWorkoutByIdUseCase;
import com.raulmartin.gym_manager.workout.domain.port.out.WorkoutRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindWorkoutByIdService implements FindWorkoutByIdUseCase{
    
    private final WorkoutRepositoryPort workoutRepositoryPort;
    
    @Override
    public Optional<Workout> findById(UUID id) {
        return workoutRepositoryPort.findById(id);
    }
    

}
