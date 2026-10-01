package com.raulmartin.gym_manager.exercise.domain.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;
import com.raulmartin.gym_manager.exercise.domain.model.MuscleGroup;

public interface ExerciseRepositoryPort {
    Exercise save(Exercise exercise);
    Optional<Exercise> findById(UUID id);
    List<Exercise> findAll();
    Page<Exercise> searchExercise(String name, MuscleGroup muscleGroup, Pageable pageable);
    void deleteById(UUID id);
}
