package com.raulmartin.gym_manager.exercise.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;
import com.raulmartin.gym_manager.exercise.domain.model.MuscleGroup;
import com.raulmartin.gym_manager.exercise.domain.port.in.SearchExerciseUseCase;
import com.raulmartin.gym_manager.exercise.domain.port.out.ExerciseRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchExerciseService implements SearchExerciseUseCase{
    
    private final ExerciseRepositoryPort exerciseRepositoryPort;
    
    @Override
    public Page<Exercise> searchExercise(String name, MuscleGroup muscleGroup, Pageable pageable) {
        return exerciseRepositoryPort.searchExercise(name, muscleGroup, pageable);
    }
    
}
