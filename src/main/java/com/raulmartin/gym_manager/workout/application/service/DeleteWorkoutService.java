package com.raulmartin.gym_manager.workout.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.workout.domain.port.in.DeleteWorkoutUseCase;
import com.raulmartin.gym_manager.workout.domain.port.out.WorkoutRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeleteWorkoutService implements DeleteWorkoutUseCase{
    
    private final WorkoutRepositoryPort workoutRepositoryPort;

    @Override
    public void deleteById(UUID id) {
        workoutRepositoryPort.findById(id).orElseThrow(() -> new IllegalArgumentException("No se ha encontrado la tabla"));
        workoutRepositoryPort.deleteById(id);
    }
    
}
