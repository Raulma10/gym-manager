package com.raulmartin.gym_manager.exercise.application.service;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;
import com.raulmartin.gym_manager.exercise.domain.model.MuscleGroup;
import com.raulmartin.gym_manager.exercise.domain.port.in.CreateExerciseUseCase;
import com.raulmartin.gym_manager.exercise.domain.port.out.ExerciseRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateExerciseService implements CreateExerciseUseCase{

    private final ExerciseRepositoryPort exerciseRepositoryPort;

    @Override
    public Exercise createExercise(String name, String description, MuscleGroup muscleGroup) {
        Exercise exercise = Exercise.create(name, description, muscleGroup);
        return exerciseRepositoryPort.save(exercise);
    }
    
}
