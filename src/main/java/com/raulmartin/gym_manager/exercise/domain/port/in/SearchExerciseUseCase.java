package com.raulmartin.gym_manager.exercise.domain.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;
import com.raulmartin.gym_manager.exercise.domain.model.MuscleGroup;

public interface SearchExerciseUseCase {
    Page<Exercise> searchExercise(String name, MuscleGroup muscleGroup, Pageable pageable);
}
