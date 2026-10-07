package com.raulmartin.gym_manager.workout.infrastructure.out.persistence.mapper;

import java.util.UUID;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.raulmartin.gym_manager.workout.domain.model.Workout;
import com.raulmartin.gym_manager.workout.infrastructure.out.persistence.WorkoutExerciseJpaEntity;
import com.raulmartin.gym_manager.workout.infrastructure.out.persistence.WorkoutJpaEntity;

@Mapper(componentModel = "spring", uses = {WorkoutExercisePersistenceMapper.class, ObjectivePersistenceMapper.class})
public interface WorkoutPersistenceMapper {
    Workout toDomain(WorkoutJpaEntity workoutJpaEntity);
    
    WorkoutJpaEntity toEntity(Workout workout);

    @AfterMapping
    default void linkWorkoutExercisesToParent(@MappingTarget WorkoutJpaEntity entity) {
        if (entity.getWorkoutExercises() != null) {
            for (WorkoutExerciseJpaEntity we : entity.getWorkoutExercises()) {
                if (we.getId() == null) {
                    we.setId(UUID.randomUUID());
                }
                we.setWorkout(entity);
            }
        }
    }
}
