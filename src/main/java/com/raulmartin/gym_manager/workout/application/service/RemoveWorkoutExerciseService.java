package com.raulmartin.gym_manager.workout.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.workout.domain.model.Workout;
import com.raulmartin.gym_manager.workout.domain.port.in.RemoveWorkoutExerciseUseCase;
import com.raulmartin.gym_manager.workout.domain.port.out.WorkoutRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class RemoveWorkoutExerciseService implements RemoveWorkoutExerciseUseCase{
    
    private final WorkoutRepositoryPort workoutRepositoryPort;

    @Override
    public Workout removeExercise(UUID workoutId, UUID exerciseId) {
        Workout workout = workoutRepositoryPort.findById(workoutId)
                .orElseThrow(() -> new IllegalArgumentException("Tabla de entrenamiento no encontrada"));
 
        Workout updated = workout.removeExercise(exerciseId);
        return workoutRepositoryPort.save(updated);
    }


}
