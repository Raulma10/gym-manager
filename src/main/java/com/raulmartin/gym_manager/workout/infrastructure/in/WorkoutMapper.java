package com.raulmartin.gym_manager.workout.infrastructure.in;

import org.mapstruct.Mapper;

import com.raulmartin.gym_manager.exercise.infrastructure.in.web.ExerciseMapper;
import com.raulmartin.gym_manager.workout.domain.model.Workout;
import com.raulmartin.gym_manager.workout.domain.model.WorkoutExercise;
import com.raulmartin.gym_manager.workout.infrastructure.in.web.dto.WorkoutExerciseResponse;
import com.raulmartin.gym_manager.workout.infrastructure.in.web.dto.WorkoutResponse;

@Mapper(componentModel = "spring", uses = ExerciseMapper.class)
public interface WorkoutMapper {
    WorkoutResponse toWorkoutResponse(Workout workout);
    WorkoutExerciseResponse toWorkoutExerciseResponse(WorkoutExercise workoutExercise);
}
