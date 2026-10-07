package com.raulmartin.gym_manager.workout.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;
import com.raulmartin.gym_manager.exercise.domain.port.out.ExerciseRepositoryPort;
import com.raulmartin.gym_manager.workout.domain.model.Objective;
import com.raulmartin.gym_manager.workout.domain.model.Workout;
import com.raulmartin.gym_manager.workout.domain.model.WorkoutExercise;
import com.raulmartin.gym_manager.workout.domain.port.in.CreateWorkoutUseCase;
import com.raulmartin.gym_manager.workout.domain.port.in.ExerciseSelection;
import com.raulmartin.gym_manager.workout.domain.port.out.WorkoutRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateWorkoutService implements CreateWorkoutUseCase {
 
    private final WorkoutRepositoryPort workoutRepositoryPort;
    private final ExerciseRepositoryPort exerciseRepositoryPort;
 
    @Override
    public Workout createWorkout(String name, String description, Objective objective,
                                  List<ExerciseSelection> exercises) {
        List<WorkoutExercise> workoutExercises = exercises.stream()
                .map(this::resolveWorkoutExercise)
                .toList();
 
        Workout workout = Workout.create(name, description, objective, workoutExercises);
        return workoutRepositoryPort.save(workout);
    }
 
    private WorkoutExercise resolveWorkoutExercise(ExerciseSelection selection) {
        Exercise exercise = exerciseRepositoryPort.findById(selection.exerciseId())
                .orElseThrow(() -> new IllegalArgumentException("No se ha encontrado el ejercicio"));
 
        return WorkoutExercise.create(exercise, selection.sets(), selection.reps(),
                selection.restSeconds(), selection.order());
    }
}
