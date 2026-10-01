package com.raulmartin.gym_manager.exercise.infrastructure.out.persistence;

import org.mapstruct.Mapper;

import com.raulmartin.gym_manager.exercise.domain.model.MuscleGroup;

@Mapper(componentModel = "spring")
public interface MuscleGroupPersistenceMapper {

    MuscleGroupJpa toEntityMuscleGroupJpa(MuscleGroup muscleGroup);
    
    MuscleGroup toDomainMuscleGroup(MuscleGroupJpa muscleGroupJpa);
}
