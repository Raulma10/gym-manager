package com.raulmartin.gym_manager.workout.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;
import com.raulmartin.gym_manager.exercise.domain.port.out.ExerciseRepositoryPort;
import com.raulmartin.gym_manager.workout.domain.model.Workout;
import com.raulmartin.gym_manager.workout.domain.model.WorkoutExercise;
import com.raulmartin.gym_manager.workout.domain.port.in.AddWorkoutExerciseUseCase;
import com.raulmartin.gym_manager.workout.domain.port.out.WorkoutRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AddWorkoutExerciseService implements AddWorkoutExerciseUseCase{
    
    private final WorkoutRepositoryPort workoutRepositoryPort;
    private final ExerciseRepositoryPort exerciseRepositoryPort;

    @Override
    public Workout addExercise(UUID workoutId, UUID exerciseId, int sets, int reps, int restSeconds, int order) {
        Workout workout = workoutRepositoryPort.findById(workoutId)
                .orElseThrow(() -> new IllegalArgumentException("Tabla de entrenamiento no encontrada"));
 
        Exercise exercise = exerciseRepositoryPort.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Ejercicio no encontrado"));
 
        WorkoutExercise newWorkoutExercise = WorkoutExercise.create(exercise, sets, reps, restSeconds, order);
        Workout updated = workout.addExercise(newWorkoutExercise);
        return workoutRepositoryPort.save(updated);
    }
    
}
