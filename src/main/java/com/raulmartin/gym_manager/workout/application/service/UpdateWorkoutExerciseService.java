package com.raulmartin.gym_manager.workout.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.workout.domain.model.Workout;
import com.raulmartin.gym_manager.workout.domain.port.in.UpdateWorkoutExerciseUseCase;
import com.raulmartin.gym_manager.workout.domain.port.out.WorkoutRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateWorkoutExerciseService implements UpdateWorkoutExerciseUseCase{
    
    private final WorkoutRepositoryPort workoutRepositoryPort;

    @Override
    public Workout updateExercise(UUID workoutId, UUID exerciseId, int sets, int reps, int restSeconds, int order) {
        Workout workout = workoutRepositoryPort.findById(workoutId)
                .orElseThrow(() -> new IllegalArgumentException("Tabla de entrenamiento no encontrada"));
 
        Workout updated = workout.updateExercise(exerciseId, sets, reps, restSeconds, order);
        return workoutRepositoryPort.save(updated);
    }


}
