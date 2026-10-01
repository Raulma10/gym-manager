package com.raulmartin.gym_manager.exercise.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;
import com.raulmartin.gym_manager.exercise.domain.model.MuscleGroup;
import com.raulmartin.gym_manager.exercise.domain.port.in.UpdateExerciseUseCase;
import com.raulmartin.gym_manager.exercise.domain.port.out.ExerciseRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateExerciseService implements UpdateExerciseUseCase{

    private final ExerciseRepositoryPort exerciseRepositoryPort;

    @Override
    public Exercise updateExercise(UUID id, String name, String description, MuscleGroup muscleGroup) {
        Exercise exercise = exerciseRepositoryPort.findById(id).orElseThrow(() -> new IllegalArgumentException("No se ha encontrado el ejercicio"));
        String newName = exercise.getName();
        String newDescription = exercise.getDescription();
        MuscleGroup newMuscleGroup = exercise.getMuscleGroup();

        if(name != null){
            newName = name;
        }

        if(description != null){
            newDescription = description;
        }

        if(muscleGroup != null){
            newMuscleGroup = muscleGroup;
        }

        Exercise updatedExercise = exercise.update(
                newName, 
                newDescription, 
                newMuscleGroup
        );

        return  exerciseRepositoryPort.save(updatedExercise);
    }
    
}
