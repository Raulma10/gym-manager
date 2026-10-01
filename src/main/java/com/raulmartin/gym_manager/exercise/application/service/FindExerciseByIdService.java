package com.raulmartin.gym_manager.exercise.application.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;
import com.raulmartin.gym_manager.exercise.domain.port.in.FindExerciseByIdUseCase;
import com.raulmartin.gym_manager.exercise.domain.port.out.ExerciseRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindExerciseByIdService implements FindExerciseByIdUseCase{
    
    private final ExerciseRepositoryPort exerciseRepositoryPort;
    
    @Override
    public Optional<Exercise> findById(UUID id) {
        return exerciseRepositoryPort.findById(id);
    }
    
}
