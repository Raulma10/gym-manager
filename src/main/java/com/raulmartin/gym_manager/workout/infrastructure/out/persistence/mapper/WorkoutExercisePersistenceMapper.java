package com.raulmartin.gym_manager.workout.infrastructure.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.raulmartin.gym_manager.workout.domain.model.WorkoutExercise;
import com.raulmartin.gym_manager.workout.infrastructure.out.persistence.WorkoutExerciseJpaEntity;

@Mapper(componentModel = "spring")
public interface WorkoutExercisePersistenceMapper {
    WorkoutExercise toDomain(WorkoutExerciseJpaEntity workoutExerciseJpaEntity);
    
    @Mapping(target = "workout", ignore = true)
    WorkoutExerciseJpaEntity toEntity(WorkoutExercise workoutExercise);
}
