package com.raulmartin.gym_manager.exercise.domain.port.in;

import java.util.List;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;

public interface ListExercisesUseCase {
    List<Exercise> listAll();
}
