package com.raulmartin.gym_manager.exercise.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;
import com.raulmartin.gym_manager.exercise.domain.port.in.ListExercisesUseCase;
import com.raulmartin.gym_manager.exercise.domain.port.out.ExerciseRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ListExercisesService implements ListExercisesUseCase{
    
    private final ExerciseRepositoryPort exerciseRepositoryPort;
    
    @Override
    public List<Exercise> listAll() {
        return exerciseRepositoryPort.findAll();
    }
    
}
