package com.raulmartin.gym_manager.exercise.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.exercise.domain.port.in.DeleteExerciseUseCase;
import com.raulmartin.gym_manager.exercise.domain.port.out.ExerciseRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeleteExerciseService implements DeleteExerciseUseCase{
    
    private final ExerciseRepositoryPort exerciseRepositoryPort;

    @Override
    public void deleteExercise(UUID id) {
        exerciseRepositoryPort.findById(id).orElseThrow(() -> new IllegalArgumentException("No se ha encontrado el ejercicio"));
        exerciseRepositoryPort.deleteById(id);

    }
    
}
