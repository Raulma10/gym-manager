package com.raulmartin.gym_manager.exercise.infrastructure.out.persistence;

import org.mapstruct.Mapper;

import com.raulmartin.gym_manager.exercise.domain.model.Exercise;

@Mapper(componentModel = "spring", uses = MuscleGroupPersistenceMapper.class)
public interface ExercisePersistenceMapper {
    
    ExerciseJpaEntity toEntity(Exercise exercise);

    Exercise toDomain(ExerciseJpaEntity exerciseJpaEntity);
}
